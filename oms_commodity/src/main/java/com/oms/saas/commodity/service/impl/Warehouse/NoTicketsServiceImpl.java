package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Warehouse.*;
import com.oms.saas.commodity.Vo.Warehouse.NoTicketsVO;
import com.oms.saas.commodity.api.DocumentState;
import com.oms.saas.commodity.api.DocumentType;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.dto.Store.SimulationStoreInfoDto;
import com.oms.saas.commodity.mapper.Warehouse.NoTicketsMapper;
import com.oms.saas.commodity.mapper.Warehouse.WmsSimulationStoreInfoMapper;
import com.oms.saas.commodity.service.Inventory.InventoryService;
import com.oms.saas.commodity.service.Warehouse.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 采购入库通知单 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-21
 */
@Service
public class NoTicketsServiceImpl extends ServiceImpl<NoTicketsMapper, NoTickets> implements NoTicketsService {

    @Resource
    private JwtInfo jwtInfo;
    @Resource
    private SnAndBrandAssociationMapperService snAndBrandAssociationMapperService;
    @Resource
    private WmsTicketsService wmsTicketsService;
    @Resource
    private WmsTicketsGoodsService wmsTicketsGoodsService;
    @Resource
    private NoTicketsGoodsService noTicketsGoodsService;
    @Resource
    private WmsSimulationStoreInfoMapper simulationStoreInfoMapper;
    @Resource
    private InventoryService inventoryService;

    @Override
    public boolean save(NoTicketsVO vo) {
        NoTickets noTickets = new NoTickets();
        BeanUtil.copyProperties(vo, noTickets);
        String batchCode = IdUtil.simpleUUID();
        String noSn = "NO" + batchCode;
        noTickets.setNoSn(noSn);
        noTickets.setBatchCode(batchCode);
        noTickets.setCompanyCode(jwtInfo.getCompanyCode());
        noTickets.setCreatedUser(jwtInfo.getNickName());
        List<String> brandCodes = vo.getBrandCodes();
        List<SnAndBrandAssociation> poInfoMapperArrayList = new ArrayList<>();
        for (String brandCode : brandCodes) {
            SnAndBrandAssociation snAndBrandAssociation = new SnAndBrandAssociation();
            snAndBrandAssociation.setBrandCode(brandCode);
            snAndBrandAssociation.setSn(noSn);
            snAndBrandAssociation.setType(DocumentType.NO.getCode());
            snAndBrandAssociation.setCompanyCode(jwtInfo.getCompanyCode());
            poInfoMapperArrayList.add(snAndBrandAssociation);
        }
        snAndBrandAssociationMapperService.saveBatch(poInfoMapperArrayList);
        return this.save(noTickets);
    }

    @Override
    public boolean examine(String noSn) {
        QueryWrapper<NoTickets> query = new QueryWrapper<>();
        query.eq("no_sn", noSn);
        NoTickets noTickets = this.getOne(query);
        Integer state = noTickets.getNoState();
        if (state != DocumentState.AUDIT.getCode()) {
            throw new RuntimeException("采购入库单非待审核状态");
        }
        createWarehousingNotificationOrder(noSn);
        return false;
    }

    /**
     * 创建入库单
     *
     * @param noSn
     * @return
     */
    public boolean createWarehousingNotificationOrder(String noSn) {
        String sn = "CG" + IdUtil.simpleUUID();
        QueryWrapper<NoTicketsGoods> query = new QueryWrapper<>();
        query.eq("no_sn", noSn);
        List<NoTicketsGoods> list = noTicketsGoodsService.list(query);
        ArrayList<WmsTicketsGoods> wmsTicketsGoodsArrayList = new ArrayList<>();
        for (NoTicketsGoods noTicketsGoods : list) {
            WmsTicketsGoods wmsTicketsGoods = new WmsTicketsGoods();
            wmsTicketsGoods.setSn(sn);
            wmsTicketsGoods.setInventoryType("ZP");
            wmsTicketsGoods.setSkuSn(noTicketsGoods.getSkuSn());
            wmsTicketsGoods.setGoodsSn(noTicketsGoods.getGoodsSn());
            wmsTicketsGoods.setBarcodeSn(noTicketsGoods.getBarcodeSn());
            wmsTicketsGoods.setGoodsName(noTicketsGoods.getGoodsName());
            wmsTicketsGoods.setBatchCode(noTicketsGoods.getBatchCode());
            wmsTicketsGoods.setPurchasePrice(noTicketsGoods.getPurchasePrice());
            wmsTicketsGoods.setNumberExpected(noTicketsGoods.getZpNumberExpected());
            wmsTicketsGoodsArrayList.add(wmsTicketsGoods);
        }
        QueryWrapper<NoTickets> query1 = new QueryWrapper<>();
        query.eq("no_sn", noSn);
        NoTickets one = this.getOne(query1);
        //WmsSimulationStoreInfo wmsSimulation = simulationStoreInfoService.getOne(new QueryWrapper<WmsSimulationStoreInfo>().eq("wms_simulation_code", one.getWmsSimulationCode()));
        SimulationStoreInfoDto simulationStoreInfo = simulationStoreInfoMapper.selectSimulationStoreInfoWtihOwnerInfo(one.getWmsSimulationCode());

        if (ObjectUtil.isEmpty(simulationStoreInfo))
            throw new RuntimeException("虚仓:" + one.getWmsSimulationCode() + "信息不存在");

        WmsTickets tickets = new WmsTickets();
        tickets.setSn(sn);
        tickets.setTicketType(DocumentState.WAREHOUSING.getCode());
        tickets.setRelationSn(one.getNoSn());
        tickets.setOriginalSn(one.getPoSn());
        tickets.setWmsSimulationCode(simulationStoreInfo.getWmsSimulationCode());
        tickets.setWmsSimulationName(simulationStoreInfo.getWmsSimulationName());
        tickets.setStoreType(DocumentState.E_COMMERCE_WAREHOUSE.getCode());
        tickets.setRemark(one.getRemarks());
        tickets.setUserName(jwtInfo.getNickName());
        tickets.setActualWarehouse(simulationStoreInfo.getOwnerInfo().getRealStoreInfo().getActualWarehouse());
        wmsTicketsService.save(tickets);
        wmsTicketsGoodsService.saveBatch(wmsTicketsGoodsArrayList);
        if (tickets.getActualWarehouse() == DocumentState.VIRTUALLY_WAREHOUSE.getCode())
            inventoryService.CGInventoryCallback(sn);
        return true;
    }
}