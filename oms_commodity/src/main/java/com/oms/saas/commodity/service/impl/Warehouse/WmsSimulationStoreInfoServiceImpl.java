package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import com.oms.saas.commodity.Entity.Warehouse.WmsRealStoreInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsSimulationStoreInfo;
import com.oms.saas.commodity.Vo.Warehouse.WmsSimulationStoreInfoVO;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.WmsSimulationStoreInfoMapper;
import com.oms.saas.commodity.service.Warehouse.WmsSimulationStoreInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 虚仓表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-08-02
 */
@Service
public class WmsSimulationStoreInfoServiceImpl extends ServiceImpl<WmsSimulationStoreInfoMapper, WmsSimulationStoreInfo> implements WmsSimulationStoreInfoService {

    @Resource
    private JwtInfo jwtInfo;

    @Override
    public boolean save(WmsSimulationStoreInfoVO vo) {
        WmsSimulationStoreInfo wmsSimulationStoreInfo = new WmsSimulationStoreInfo();
        BeanUtil.copyProperties(vo,wmsSimulationStoreInfo);
        wmsSimulationStoreInfo.setCompanyCode(jwtInfo.getCompanyCode());
        return this.save(wmsSimulationStoreInfo);
    }
}
