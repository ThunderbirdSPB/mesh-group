package com.group.mesh.config.log;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class IntegrationLogAspect {
    @Pointcut("@annotation(an)")
    public void isLoggable(IntegrationLog an) {
    }

    @Around(value = "isLoggable(an)", argNames = "jp,an")
    public Object log(ProceedingJoinPoint jp, IntegrationLog an) throws Throwable {
        try {
            log.info("Start handling request with id = {}, uri = {}, method = {}, input params = {}",
                    LogAttributesHolder.getRequestID(), LogAttributesHolder.getURI(), LogAttributesHolder.getHttpMethod(),
                    getReqParams(jp));

            val result = jp.proceed();

            log.info("Successfully finished handling request with id = {}", LogAttributesHolder.getRequestID());
            return result;
        } catch (Throwable e) {
            log.error(String.format("Error while handling request with id = %s", LogAttributesHolder.getRequestID()), e);
            throw e;
        }
    }

    private Map<String, Object> getReqParams(ProceedingJoinPoint jp) {
        val names = ((CodeSignature) jp.getSignature()).getParameterNames();
        val values = jp.getArgs();

        val map = new HashMap<String, Object>(names.length);
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], values[i]);
        }

        return map;
    }


}
