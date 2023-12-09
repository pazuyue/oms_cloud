package com.saas.common.security.service.impl.Brand;

import com.saas.common.security.entity.Brand.UserBrands;
import com.saas.common.security.mapper.Brand.UserBrandsMapper;
import com.saas.common.security.service.Brand.UserBrandsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌关联表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-12-09
 */
@Service
public class UserBrandsServiceImpl extends ServiceImpl<UserBrandsMapper, UserBrands> implements UserBrandsService {

}
