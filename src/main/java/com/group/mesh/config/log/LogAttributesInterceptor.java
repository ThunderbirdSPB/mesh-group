package com.group.mesh.config.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * This interceptor is used for generating request id
 */
@Slf4j
@ControllerAdvice
public class LogAttributesInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogAttributesHolder.addRequestID(UUID.randomUUID().toString());
        LogAttributesHolder.addURI(request.getRequestURI());
        LogAttributesHolder.addHttpMethod(request.getMethod());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LogAttributesHolder.clear();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
