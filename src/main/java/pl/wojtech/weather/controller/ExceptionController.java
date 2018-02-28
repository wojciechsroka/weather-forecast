package pl.wojtech.weather.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wojtech on 2018-02-28.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Exception e)   {
        return "error";
    }
}
