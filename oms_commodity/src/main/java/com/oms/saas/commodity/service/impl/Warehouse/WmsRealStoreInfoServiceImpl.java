package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsRealStoreInfo;
import com.oms.saas.commodity.Vo.Warehouse.WmsRealStoreInfoVO;
import com.oms.saas.commodity.mapper.Warehouse.WmsRealStoreInfoMapper;
import com.oms.saas.commodity.service.Warehouse.WmsRealStoreInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 实仓表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-18
 */
@Service
public class WmsRealStoreInfoServiceImpl extends ServiceImpl<WmsRealStoreInfoMapper, WmsRealStoreInfo> implements WmsRealStoreInfoService {

    @Override
    public boolean save(WmsRealStoreInfoVO vo) {
        WmsRealStoreInfo infoVO = findOneByOwnerInfoVO(vo);
        if (!ObjectUtil.isEmpty(infoVO))
            throw new RuntimeException("实仓编码"+infoVO.getRealStoreCode()+"已存在");
        WmsRealStoreInfo wmsRealStoreInfo = new WmsRealStoreInfo();
        BeanUtil.copyProperties(vo,wmsRealStoreInfo);
        if (this.save(wmsRealStoreInfo))
            return true;
        return false;
    }

    @Override
    public WmsRealStoreInfo findOneByOwnerInfoVO(WmsRealStoreInfoVO vo) {
        String realStoreCode = vo.getRealStoreCode();
        QueryWrapper<WmsRealStoreInfo> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(realStoreCode)){
            queryWrapper.eq("real_store_code",realStoreCode);
        }
        return this.getOne(queryWrapper);
    }
}
