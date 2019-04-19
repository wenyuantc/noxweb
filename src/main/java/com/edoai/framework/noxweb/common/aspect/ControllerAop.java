package com.edoai.framework.noxweb.common.aspect;

import com.edoai.framework.noxweb.common.extension.api.Result;
import com.edoai.framework.noxweb.common.extension.enums.ApiErrorCode;
import com.edoai.framework.noxweb.common.extension.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Nox (HuangWenYuan)
 * @description: Controller 异常处理Aop
 * @create: 2019-04-19 11:59
 **/
@Aspect
@Component
@Slf4j
public class ControllerAop {


    @Around("execution(com.edoai.framework.noxweb.common.extension.api.Result *(..)))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Result<?> result;

        try {
            result = (Result<?>) pjp.proceed();
            log.info(pjp.getSignature() + "use time: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    /**
     * 封装异常信息，注意区分已知异常（自己抛出的）和未知异常
     */
    private Result<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        Result<?> result = new Result();

        // 已知异常
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            result.setMsg(apiException.getErrorCode().getMsg());
            result.setCode(apiException.getErrorCode().getCode());
        } else {
            log.error(pjp.getSignature() + " error ", e);
            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(e.toString());
            result.setCode(ApiErrorCode.FAILED.getCode());
        }

        return result;
    }
}
