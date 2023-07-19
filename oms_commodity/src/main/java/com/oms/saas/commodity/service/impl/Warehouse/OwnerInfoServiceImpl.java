package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.OwnerInfoMapper;
import com.oms.saas.commodity.service.Warehouse.OwnerInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OwnerInfoServiceImpl extends ServiceImpl<OwnerInfoMapper, OwnerInfo> implements OwnerInfoService {
    @Resource
    private JwtInfo jwtInfo;

    @Override
    public boolean save(OwnerInfoVO vo) {
        OwnerInfo ownerInfo = new OwnerInfo();
        OwnerInfo one = findOneByOwnerInfoVO(vo);
        if (!ObjectUtil.isEmpty(one))
            throw new RuntimeException("货主编码"+one.getOwnerCode()+"已存在");
        System.out.println(jwtInfo);
        BeanUtil.copyProperties(vo,ownerInfo);
        ownerInfo.setCompanyCode(jwtInfo.getCompanyCode());
        if (this.save(ownerInfo))
            return true;
        return false;
    }

    @Override
    public OwnerInfo findOneByOwnerInfoVO(OwnerInfoVO vo) {
        String ownerCode = vo.getOwnerCode();
        QueryWrapper<OwnerInfo> queryWrapper = new <OwnerInfo>QueryWrapper();
        if (!StrUtil.isBlank(ownerCode)){
            queryWrapper.eq("owner_code",ownerCode);
        }
        return this.getOne(queryWrapper);
    }

    @Override
    public Page<OwnerInfo> list(String owner_code, Integer page, Integer pageSize) {
        QueryWrapper<OwnerInfo> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(owner_code)){
            queryWrapper.eq("owner_code",owner_code);
        }
        queryWrapper.orderByDesc("modify_time");
        return this.page(new Page<>(page, pageSize),queryWrapper);

    }

}
