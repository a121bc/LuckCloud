package com.ltj.gateway.common;

import com.ltj.gateway.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @author: LIU Tian Jun
 * @Date: 2019/12/12 10:48
 * @describe： spring mvc restful 统一异常拦截器
 * @version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Mono<Result> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error("could_not_read_json... {}", e.getMessage());
        return Mono.just(Result.error500("请求参数不正确", e.getMessage()));
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Mono<Result> handleValidationException(MethodArgumentNotValidException e) {
        log.error("parameter_validation_exception... ", e);
        return Mono.just(Result.error400("请求类型不支持！", e.getMessage()));
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<Result> globalException(Exception e) {
        log.error("other error... ", e);
        return Mono.just(Result.error500(e.getMessage(), e.getCause().toString()));
    }

}
