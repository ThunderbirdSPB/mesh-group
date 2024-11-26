package com.group.mesh.web;

import com.group.mesh.web.dto.BaseResponseV1Dto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * If i had time and documentation for API, i'd make more handy error typing.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public BaseResponseV1Dto defaultErrorHandler(HttpServletRequest req, Exception e)  {
        log.error("Exception at request " + req.getRequestURL(), e);
        return BaseResponseV1Dto.prepareErrorResponse(e.getMessage());
    }
}
