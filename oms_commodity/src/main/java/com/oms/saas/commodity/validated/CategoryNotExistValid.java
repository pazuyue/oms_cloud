package com.oms.saas.commodity.validated;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Archives.GoodsCategory;
import com.oms.saas.commodity.mapper.geberator.GoodsCategoryMapper;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CategoryNotExistValid implements ConstraintValidator<CategoryNotExist,String> {

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public void initialize(CategoryNotExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!ObjectUtils.isEmpty(s)) {
            return isExist(s);
        }else{
            return false;
        }
    }

    public boolean isExist(String s)
    {
       try {
           QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper<>();
           queryWrapper.eq("name",s);
           GoodsCategory category = goodsCategoryMapper.selectOne(queryWrapper);
           if (ObjectUtils.isEmpty(category)){
               return false;
           }else {
               return true;
           }
       }catch (Throwable throwable){
           System.out.println(throwable);
       }
       return false;
    }
}
