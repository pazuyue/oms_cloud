package com.saas.common.security.service.Brand;

import com.saas.common.security.api.Result;
import com.saas.common.security.entity.Brand.BrandInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌信息表 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
public interface BrandInfoService extends IService<BrandInfo> {
    List<BrandInfo> getBrandByUserId(Integer userId);
}
