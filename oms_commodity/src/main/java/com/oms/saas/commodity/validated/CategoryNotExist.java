package com.oms.saas.commodity.validated;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryNotExistValid.class)
public @interface CategoryNotExist {

    String message() default "分类不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
