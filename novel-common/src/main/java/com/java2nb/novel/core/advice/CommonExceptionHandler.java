package com.java2nb.novel.core.advice;

import io.github.xxyopen.model.resp.RestResult;
import io.github.xxyopen.model.resp.SysResultCode;
import io.github.xxyopen.web.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理器
 *
 * @author xiongxiaoyang
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    public CommonExceptionHandler() {
    }

    @ExceptionHandler({BindException.class})
    public RestResult<Void> handlerBindException(BindException e) {
        log.error(e.getMessage(), e);
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getDefaultMessage())
                .filter(m -> m != null && !m.isEmpty())
                .findFirst().orElse(SysResultCode.PARAM_ERROR.getMsg());
        RestResult<Void> result = new RestResult<>();
        result.setCode(SysResultCode.PARAM_ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    @ExceptionHandler({BusinessException.class})
    public RestResult<Void> handlerBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RestResult.fail(e.getResultCode());
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        if (isJsonRequest(request)) {
            // 如果是REST请求，返回JSON格式的错误响应
            return RestResult.error();
        } else {
            //跳转页面过程中出现异常时统一跳转到404页面
            return new ModelAndView("404");
        }
    }

    private boolean isJsonRequest(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        return acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE);
    }

}