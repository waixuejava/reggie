package com.li.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @Author LI
 * @create 2022/6/26 10:22
 */
@Slf4j
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public R<String> exceptionHandler(SQLException sqlException){
        log.info("出现sql异常：{}",sqlException);
        return R.error("出现异常");
    }
    @ExceptionHandler(CustomException.class)
    public R<String> customerException(CustomException customException){
        return R.error(customException.getMessage());
    }
    @ExceptionHandler(ArithmeticException.class)
    public R<String> arithmeticException(ArithmeticException arithmeticException){
        return R.error("数学运算异常");
    }
}
