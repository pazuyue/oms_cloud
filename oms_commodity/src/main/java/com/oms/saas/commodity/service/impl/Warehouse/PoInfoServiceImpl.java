package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.oms.saas.commodity.Entity.Warehouse.PoInfo;
import com.oms.saas.commodity.Entity.Warehouse.PoInfoBrandMapper;
import com.oms.saas.commodity.Vo.Warehouse.PoInfoVO;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.PoInfoMapper;
import com.oms.saas.commodity.service.Warehouse.PoInfoBrandMapperService;
import com.oms.saas.commodity.service.Warehouse.PoInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购单主表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
@Service
public class PoInfoServiceImpl extends ServiceImpl<PoInfoMapper, PoInfo> implements PoInfoService {

    @Resource
    private JwtInfo jwtInfo;
    @Resource
    private PoInfoBrandMapperService poInfoBrandMapperService;
    @Override
    public boolean save(PoInfoVO vo) {
        String posn = "PO_"+IdUtil.simpleUUID();
        PoInfo poInfo = new PoInfo();
        BeanUtil.copyProperties(vo,poInfo);
        poInfo.setPoSn(posn);
        poInfo.setCompanyCode(jwtInfo.getCompanyCode());
        this.save(poInfo);
        List<String> brandCodes = vo.getBrandCodes();
        List<PoInfoBrandMapper> poInfoMapperArrayList =new ArrayList<>();

        for (String brandCode : brandCodes) {
            PoInfoBrandMapper poInfoMapper = new PoInfoBrandMapper();
            poInfoMapper.setBrandCode(brandCode);
            poInfoMapper.setPoSn(posn);
            poInfoMapperArrayList.add(poInfoMapper);
        }
        return poInfoBrandMapperService.saveBatch(poInfoMapperArrayList);
    }
}
