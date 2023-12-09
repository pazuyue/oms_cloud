package com.saas.common.security.service.impl.Brand;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saas.common.security.entity.Brand.BrandInfo;
import com.saas.common.security.entity.Brand.UserBrands;
import com.saas.common.security.mapper.Brand.BrandInfoMapper;
import com.saas.common.security.service.Brand.BrandInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saas.common.security.service.Brand.UserBrandsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 品牌信息表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
@Service
public class BrandInfoServiceImpl extends ServiceImpl<BrandInfoMapper, BrandInfo> implements BrandInfoService {

    @Resource
    private UserBrandsService userBrandsService;
    @Override
    public List<BrandInfo> getBrandByUserId(Integer userId) {
        List<UserBrands> brands = userBrandsService.list(new QueryWrapper<UserBrands>().eq("user_id", userId));
        QueryWrapper<BrandInfo> queryWrapper = new QueryWrapper<>();
        List<Integer> collect = brands.stream().map(UserBrands::getId).collect(Collectors.toList());
        System.out.println("brands:"+collect.toString());
        queryWrapper.in("id",collect);
        return this.list(queryWrapper);
    }
}
