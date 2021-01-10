package io.github.ihelin.seven.product.exception;

import io.github.ihelin.seven.common.utils.R;
import org.apache.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/10 下午10:12
 */
@RestControllerAdvice(basePackages = "io.github.ihelin.seven.product.controller")
public class SevenExceptionControllerAdvice {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        result.getFieldErrors().forEach(item -> {
            String message = item.getDefaultMessage();
            String field = item.getField();
            errorMap.put(field, message);
        });
        return R.error(HttpStatus.SC_BAD_REQUEST, "提交的数据不合法").put("data", errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        return R.error();
    }

}
