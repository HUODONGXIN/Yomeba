package com.java2nb.common.aspect;

import com.java2nb.common.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution( * com.java2nb..controller.*.*(..))")//2つの..はすべてのサブディレクトリを表し、最後の括弧内の2つの..はすべてのパラメータを表します
    public void logPointCut() {
    }


    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // リクエストを受信し、リクエスト内容を記録します
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // リクエスト内容を記録します
        logger.info("リクエストURL : " + request.getRequestURL().toString());
        logger.info("HTTP METHOD : " + request.getMethod());
        // 実際のIPアドレスを取得
        logger.info("IP : " + IPUtils.getIpAddr(request));
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
            + joinPoint.getSignature().getName());
        logger.info("パラメータ : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning の値と doAfterReturning の引数名を一致させます
    public void doAfterReturning(Object ret) throws Throwable {
        // リクエスト処理後、内容を返します（戻り値が複雑な場合、出力されるのは物理メモリアドレスです）
        logger.debug("戻り値 : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob はメソッドの戻り値です
        logger.info("処理時間 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}
