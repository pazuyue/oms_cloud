package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfo;
import com.oms.saas.commodity.Entity.Warehouse.NoTickets;
import com.oms.saas.commodity.Entity.Warehouse.NoTicketsGoods;
import com.oms.saas.commodity.Entity.Warehouse.NoTicketsGoodsTmp;
import com.oms.saas.commodity.Vo.Export.NoTicketsGoodsTmpVO;
import com.oms.saas.commodity.api.DocumentState;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.NoTicketsGoodsTmpMapper;
import com.oms.saas.commodity.service.Goods.GoodsSkuSnInfoService;
import com.oms.saas.commodity.service.Warehouse.NoTicketsGoodsService;
import com.oms.saas.commodity.service.Warehouse.NoTicketsGoodsTmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oms.saas.commodity.service.Warehouse.NoTicketsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 入库通知单明细-未送审 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-08-01
 */
@Service
public class NoTicketsGoodsTmpServiceImpl extends ServiceImpl<NoTicketsGoodsTmpMapper, NoTicketsGoodsTmp> implements NoTicketsGoodsTmpService {

    @Resource
    private GoodsSkuSnInfoService goodsSkuSnInfoService;
    @Resource
    private NoTicketsGoodsService noTicketsGoodsService;
    @Resource
    private NoTicketsService noTicketsService;
    @Resource
    private JwtInfo jwtInfo;

    @Override
    public boolean save(List<NoTicketsGoodsTmpVO> list, String noSn) {
        String batchCode = String.valueOf(System.currentTimeMillis());
        List<NoTicketsGoodsTmp> tmpList = new ArrayList<>();
        for (NoTicketsGoodsTmpVO vo : list) {
            NoTicketsGoodsTmp tmp = formatNoTicketsGoodsTmp(vo, noSn, batchCode);
            tmpList.add(tmp);
        }
        return this.saveBatch(tmpList);
    }

    @Override
    public boolean submitExamine(String noSn) {
        QueryWrapper<NoTicketsGoodsTmp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no_sn", noSn);
        queryWrapper.isNull("error_info");
        List<NoTicketsGoodsTmp> list = this.list(queryWrapper);
        if (ObjectUtil.isEmpty(list))
            return false;
        int numberExpected = 0;
        BigDecimal priceExpected=new BigDecimal(0);
        List<NoTicketsGoods> ticketsGoodsList = new ArrayList<>();
        for (NoTicketsGoodsTmp tmp : list) {
            NoTicketsGoods noTicketsGoods = formatNoTicketsGoods(tmp);
            ticketsGoodsList.add(noTicketsGoods);
            numberExpected = numberExpected + noTicketsGoods.getZpNumberExpected();
            priceExpected = priceExpected.add(noTicketsGoods.getPurchasePrice());
        }
        return saveNoTicketsGoods(ticketsGoodsList,numberExpected,priceExpected,noSn);
    }

    /**
     * 保存提交审核信息并且更新入库通知单状态
     * @param ticketsGoodsList
     * @param numberExpected
     * @param priceExpected
     * @param noSn
     * @return
     */
    @Transactional
    public boolean saveNoTicketsGoods(List<NoTicketsGoods> ticketsGoodsList,int numberExpected,BigDecimal priceExpected,String noSn){
        noTicketsGoodsService.saveBatch(ticketsGoodsList);
        NoTickets noTickets = new NoTickets();
        noTickets.setNumberExpected(numberExpected);
        noTickets.setPriceExpected(priceExpected);
        noTickets.setNoState(DocumentState.AUDIT.getCode());
        UpdateWrapper<NoTickets> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("no_sn",noSn);
        return noTicketsService.update(noTickets,updateWrapper);
    }

    public NoTicketsGoods formatNoTicketsGoods(NoTicketsGoodsTmp tmp) {
        NoTicketsGoods ticketsGoods = new NoTicketsGoods();
        ticketsGoods.setNoSn(tmp.getNoSn());
        ticketsGoods.setSkuSn(tmp.getSkuSn());
        ticketsGoods.setBarcodeSn(tmp.getBarcodeSn());
        ticketsGoods.setBatchCode(tmp.getBatchCode());
        ticketsGoods.setGoodsSn(tmp.getGoodsSn());
        ticketsGoods.setGoodsName(tmp.getGoodsName());
        ticketsGoods.setPurchasePrice(tmp.getPurchasePrice());
        ticketsGoods.setZpNumberExpected(tmp.getZpNumberExpected());
        ticketsGoods.setCompanyCode(jwtInfo.getCompanyCode());
        return ticketsGoods;
    }

    /**
     * 格式化导入信息
     *
     * @param vo
     * @return
     */
    public NoTicketsGoodsTmp formatNoTicketsGoodsTmp(NoTicketsGoodsTmpVO vo, String noSn, String batchCode) {
        NoTicketsGoodsTmp tmp = new NoTicketsGoodsTmp();
        String skuSn = vo.getSkuSn();
        BigDecimal purchasePrice = vo.getPurchasePrice();
        int zpNumberExpected = vo.getZpNumberExpected();
        QueryWrapper<GoodsSkuSnInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_sn", skuSn);
        GoodsSkuSnInfo one = goodsSkuSnInfoService.getOne(queryWrapper);
        tmp.setBatchCode(batchCode);
        tmp.setNoSn(noSn);
        tmp.setSkuSn(skuSn);
        tmp.setPurchasePrice(purchasePrice);
        tmp.setZpNumberExpected(zpNumberExpected);
        tmp.setCompanyCode(jwtInfo.getCompanyCode());
        if (ObjectUtil.isEmpty(one)) {
            tmp.setErrorInfo("导入商品不存在");
        }
        tmp.setGoodsSn(one.getGoodsSn());
        tmp.setBarcodeSn(one.getBarcodeSn());
        tmp.setGoodsName(one.getGoodsName());

        return tmp;
    }
}
