package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.SupplierInfo;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.Vo.Warehouse.SupplierInfoVO;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.SupplierInfoMapper;
import com.oms.saas.commodity.service.Warehouse.SupplierInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商主表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-19
 */
@Service
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoMapper, SupplierInfo> implements SupplierInfoService {

    @Resource
    private JwtInfo jwtInfo;

    public boolean save(SupplierInfoVO vo) {
        SupplierInfo one = findOneByOwnerInfoVO(vo);
        if (!ObjectUtil.isEmpty(one))
            throw new RuntimeException("供应商主表"+one.getSupplierSn()+"已存在");
        SupplierInfo supplierInfo = new SupplierInfo();
        BeanUtil.copyProperties(vo,supplierInfo);
        String po_sn =  IdUtil.simpleUUID();
        supplierInfo.setSupplierSn(po_sn);
        supplierInfo.setCompanyCode(jwtInfo.getCompanyCode());
        if (this.save(supplierInfo))
            return true;
        return false;
    }

    @Override
    public SupplierInfo findOneByOwnerInfoVO(SupplierInfoVO vo) {
        QueryWrapper<SupplierInfo> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(vo.getSupplierName())){
            queryWrapper.eq("supplier_name",vo.getSupplierName());
        }

        if (!StrUtil.isBlank(vo.getCompanyName())){
            queryWrapper.eq("company_name",vo.getCompanyName());
        }

        return this.getOne(queryWrapper);
    }

    @Override
    public Page<SupplierInfo> list(SupplierInfoVO vo, Integer page, Integer pageSize) {
        QueryWrapper<SupplierInfo> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(vo.getCompanyName())){
            queryWrapper.eq("company_name",vo.getCompanyName());
        }
        if (!StrUtil.isBlank(vo.getSupplierName())){
            queryWrapper.eq("supplier_name",vo.getSupplierName());
        }
        queryWrapper.orderByDesc("modify_time");
        return this.page(new Page<>(page, pageSize),queryWrapper);

    }
}
