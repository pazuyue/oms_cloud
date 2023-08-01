package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfo;
import com.oms.saas.commodity.Entity.Warehouse.NoTicketsGoodsTmp;
import com.oms.saas.commodity.Vo.Export.NoTicketsGoodsTmpVO;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.NoTicketsGoodsTmpMapper;
import com.oms.saas.commodity.service.Goods.GoodsSkuSnInfoService;
import com.oms.saas.commodity.service.Warehouse.NoTicketsGoodsTmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
    JwtInfo jwtInfo;
    @Override
    public boolean save(List<NoTicketsGoodsTmpVO> list,String noSn) {
        String batchCode =String.valueOf(System.currentTimeMillis());
        List<NoTicketsGoodsTmp> tmpList =new ArrayList<>();
        for (NoTicketsGoodsTmpVO vo : list){
            NoTicketsGoodsTmp tmp = formatNoTicketsGoodsTmp(vo,noSn,batchCode);
            tmpList.add(tmp);
        }
        return this.saveBatch(tmpList);
    }

    /**
     * 格式化导入信息
     * @param vo
     * @return
     */
    public NoTicketsGoodsTmp formatNoTicketsGoodsTmp(NoTicketsGoodsTmpVO vo,String noSn,String batchCode){
        NoTicketsGoodsTmp tmp = new NoTicketsGoodsTmp();
        String skuSn = vo.getSkuSn();
        BigDecimal purchasePrice = vo.getPurchasePrice();
        int zpNumberExpected = vo.getZpNumberExpected();
        QueryWrapper<GoodsSkuSnInfo> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("sku_sn",skuSn);
        GoodsSkuSnInfo one = goodsSkuSnInfoService.getOne(queryWrapper);
        tmp.setBatchCode(batchCode);
        tmp.setNoSn(noSn);
        tmp.setSkuSn(skuSn);
        tmp.setPurchasePrice(purchasePrice);
        tmp.setZpNumberExpected(zpNumberExpected);
        tmp.setCompanyCode(jwtInfo.getCompanyCode());
        if (ObjectUtil.isEmpty(one)){
            tmp.setErrorInfo("导入商品不存在");
        }
        tmp.setGoodsSn(one.getGoodsSn());
        tmp.setBarcodeSn(one.getBarcodeSn());
        tmp.setGoodsName(one.getGoodsName());

        return tmp;
    }
}
