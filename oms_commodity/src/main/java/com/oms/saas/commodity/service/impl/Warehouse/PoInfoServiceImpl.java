package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.oms.saas.commodity.Entity.Warehouse.PoInfo;
import com.oms.saas.commodity.Entity.Warehouse.SnAndBrandAssociation;
import com.oms.saas.commodity.Vo.Warehouse.PoInfoVO;
import com.oms.saas.commodity.api.DocumentType;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.PoInfoMapper;
import com.oms.saas.commodity.service.Warehouse.PoInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oms.saas.commodity.service.Warehouse.SnAndBrandAssociationMapperService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
    private SnAndBrandAssociationMapperService poInfoBrandMapperService;
    @Override
    public boolean save(PoInfoVO vo) {
        String posn = "PO_"+IdUtil.simpleUUID();
        PoInfo poInfo = new PoInfo();
        BeanUtil.copyProperties(vo,poInfo);
        poInfo.setPoSn(posn);
        poInfo.setCompanyCode(jwtInfo.getCompanyCode());
        this.save(poInfo);
        List<String> brandCodes = vo.getBrandCodes();
        List<SnAndBrandAssociation> poInfoMapperArrayList =new ArrayList<>();

        for (String brandCode : brandCodes) {
            SnAndBrandAssociation snAndBrandAssociation = new SnAndBrandAssociation();
            snAndBrandAssociation.setBrandCode(brandCode);
            snAndBrandAssociation.setSn(posn);
            snAndBrandAssociation.setType(DocumentType.PO.getCode());
            snAndBrandAssociation.setCompanyCode(jwtInfo.getCompanyCode());
            poInfoMapperArrayList.add(snAndBrandAssociation);
        }
        return poInfoBrandMapperService.saveBatch(poInfoMapperArrayList);
    }

    @Override
    public boolean cancel(Integer id) {
        PoInfo poInfo = new PoInfo();
        poInfo.setPoState((byte) -1);
        poInfo.setId(id);
        return this.updateById(poInfo);
    }
}
