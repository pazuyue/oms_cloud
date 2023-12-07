package com.oms.saas.inventory.Config;


import com.oms.saas.inventory.api.Result;
import com.oms.saas.inventory.api.ResultCode;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ParamException {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        System.out.println("handleMethodArgumentNotValidException:"+msg);
        return Result.failed(ResultCode.FAILED,msg);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleConstraintViolationException(ConstraintViolationException ex) {
        System.out.println("handleConstraintViolationException:"+ex.getMessage());
        return Result.failed(ResultCode.PARAM_ERROR,ex.getMessage());
    }

    @ExceptionHandler({ConstraintDeclarationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleConstraintDeclarationException(ConstraintDeclarationException ex) {
        System.out.println("handleConstraintViolationException:"+ex.getMessage());
        return Result.failed(ResultCode.PARAM_ERROR,ex.getMessage());
    }


    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleRuntimeException(RuntimeException ex) {
        System.out.println("RuntimeException:"+ex.getMessage());
        return Result.failed(ResultCode.FAILED,ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleException(Exception ex) {
        System.out.println("Exception:"+ex.getMessage());
        return Result.failed(ResultCode.FAILED,ex.getMessage());
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleSQLException(SQLException ex) {
        System.out.println("SQLNonTransientException:"+ex.getMessage());
        return Result.failed(ResultCode.FAILED,ex.getMessage());
    }
}
