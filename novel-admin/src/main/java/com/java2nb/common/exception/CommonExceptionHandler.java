package com.java2nb.common.exception;

import com.java2nb.common.config.Constant;
import com.java2nb.common.domain.LogDO;
import com.java2nb.common.service.LogService;
import com.java2nb.common.utils.HttpServletUtils;
import com.java2nb.common.utils.R;
import com.java2nb.common.utils.ShiroUtils;
import com.java2nb.system.domain.UserDO;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 例外ハンドラー
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LogService logService;

    @Autowired
    com.java2nb.common.service.BugLogService bugLogService;

    /**
     * カスタム業務例外の処理
     */
    @ExceptionHandler(BusinessException.class)
    public R handleBusinessException(BusinessException e) {
        logger.error(e.getMessage(), e);
        return R.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return R.error("このレコードは既にデータベースに存在します");
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public R noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
        logger.error(e.getMessage(), e);
        return R.error(404, "ページが見つかりません");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return R.error(403, "未認証です");
        }
        return new ModelAndView("error/403");
    }


    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        LogDO logDO = new LogDO();
        logDO.setGmtCreate(new Date());
        logDO.setOperation(Constant.LOG_ERROR);
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(e.toString());
        UserDO current = ShiroUtils.getUser();
        if(null!=current){
            logDO.setUserId(current.getUserId());
            logDO.setUsername(current.getUsername());
        }
        logService.save(logDO);
        // bug_log に書き込み
        try {
            com.java2nb.common.domain.BugLogDO bug = new com.java2nb.common.domain.BugLogDO();
            bug.setLevel("ERROR");
            bug.setMessage(e.getMessage() != null ? e.getMessage() : e.toString());
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement el : e.getStackTrace()) sb.append(el.toString()).append("\n");
            bug.setStack(sb.toString());
            bug.setUrl(request.getRequestURL().toString());
            bug.setParams(request.getQueryString());
            bug.setIp(request.getRemoteAddr());
            bug.setUserAgent(request.getHeader("User-Agent"));
            bug.setGmtCreate(new Date());
            bugLogService.save(bug);
        } catch (Exception ignored) {}
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return R.error(500, "サーバーエラーです。管理者に連絡してください");
        }
        return new ModelAndView("error/500");
    }
}
