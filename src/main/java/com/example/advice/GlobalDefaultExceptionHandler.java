package com.example.advice;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by tianfeng on 2017/12/22.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public void handleException(HttpServletRequest req, HttpServletResponse res, Exception e) throws IOException {

        logger.info("req ip:{}, port:{},url:{}", req.getRemoteAddr(), req.getRemotePort(), req.getRequestURL());
        e.printStackTrace();
        logger.error("GlobalDefaultExceptionHandler.defaultErrorHandler()");

        final OutputStream os = res.getOutputStream();
        os.write("exception occurred ... ".getBytes(Charset.forName("UTF-8")));
    }
}
