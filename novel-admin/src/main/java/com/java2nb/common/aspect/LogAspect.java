package com.java2nb.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.java2nb.common.service.LogService;
import com.java2nb.system.domain.UserToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.java2nb.common.annotation.Log;
import com.java2nb.common.dao.LogDao;
import com.java2nb.common.domain.LogDO;
import com.java2nb.common.utils.HttpContextUtils;
import com.java2nb.common.utils.IPUtils;
import com.java2nb.common.utils.JSONUtils;
import com.java2nb.common.utils.ShiroUtils;
import com.java2nb.system.domain.UserDO;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    LogService logService;


    @Pointcut("@annotation(com.java2nb.common.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // メソッドを実行
        Object result = point.proceed();
        // 実行時間（ミリ秒）
        long time = System.currentTimeMillis() - beginTime;
        // 非同期でログを保存
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            // アノテーション上の説明
            sysLog.setOperation(syslog.value());
        }
        // リクエストされたメソッド名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // リクエストされたパラメータ
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            sysLog.setParams(params);
        } catch (Exception e) {

        }
        // request を取得
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // IPアドレスを設定
        sysLog.setIp(IPUtils.getIpAddr(request));
        // ユーザー名
        UserDO currUser = ShiroUtils.getUser();
        if (null == currUser) {
            if (null != sysLog.getParams()) {
                sysLog.setUserId(-1L);
                sysLog.setUsername(sysLog.getParams());
            } else {
                sysLog.setUserId(-1L);
                sysLog.setUsername("ユーザー情報の取得が空です");
            }
        } else {
            sysLog.setUserId(ShiroUtils.getUserId());
            sysLog.setUsername(ShiroUtils.getUser().getUsername());
        }
        sysLog.setTime((int) time);
        // システム現在時刻
        Date date = new Date();
        sysLog.setGmtCreate(date);
        // システムログを保存
        logService.save(sysLog);
    }
}
