package com.saas.common.security.service.impl.Brand;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saas.common.security.entity.Brand.BrandInfo;
import com.saas.common.security.mapper.Brand.BrandInfoMapper;
import com.saas.common.security.service.Brand.BrandInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<BrandInfo> getBrandByUserId(Integer userId) {
        QueryWrapper<BrandInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return this.list(queryWrapper);
    }
}
