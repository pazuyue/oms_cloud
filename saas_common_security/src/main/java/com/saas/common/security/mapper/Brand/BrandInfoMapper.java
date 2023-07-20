package com.saas.common.security.mapper.Brand;

import com.saas.common.security.dto.UserDTO;
import com.saas.common.security.entity.Brand.BrandInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 品牌信息表 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
public interface BrandInfoMapper extends BaseMapper<BrandInfo> {

    @Select("select * from brand_info WHERE user_id =#{user_id}")
    List<BrandInfo> selectBranInfoByUserId(int user_id);
}
