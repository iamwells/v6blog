package io.github.iamwells.v6blog.server.controller;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类，用于捕获并处理所有控制器抛出的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理方法参数验证异常
     *
     * @param ex 方法参数验证异常对象
     * @return 返回包含错误信息的响应实体
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SaResult handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 创建一个错误信息的映射表
        Map<String, String> errors = new HashMap<>();
        // 遍历所有错误信息
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // 获取错误信息对应的字段名
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                // 获取错误信息
                String errorMessage = error.getDefaultMessage();
                // 将字段名和错误信息存入映射表中
                errors.put(fieldName, errorMessage);
            } else if (error instanceof ObjectError) {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        });
        // 返回包含所有错误信息的响应实体，并设置HTTP状态码为400
        return SaResult.error(errors.values().toString()).setCode(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * 处理通用异常
     * <p>
     * 该方法用于捕获和处理全局范围内的所有未处理异常，并返回统一的错误响应
     * 它通过@ExceptionHandler和@ResponseStatus注解实现对异常的捕捉和HTTP状态码的定义
     *
     * @param ex 通用异常对象，代表捕获到的异常实例
     * @return 返回包含错误信息的响应实体，以便客户端能够了解服务器端发生的错误
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public SaResult handleException(Exception ex) {
        return SaResult.error(ex.getMessage());
    }

    /**
     * 处理SaToken异常的全局处理器
     * 当抛出SaTokenException时，此方法将被调用，返回一个特定的错误响应
     *
     * @param ex SaTokenException实例，包含具体的错误信息
     * @return 返回一个表示错误结果的SaResult对象，其中包含了错误信息和HTTP状态码
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SaTokenException.class)
    public SaResult handleSaTokenExceptions(SaTokenException ex) {
        // 创建并返回一个错误结果对象，包含异常消息和FORBIDDEN状态码
        return SaResult.error(ex.getMessage()).setCode(HttpStatus.FORBIDDEN.value());
    }

}
