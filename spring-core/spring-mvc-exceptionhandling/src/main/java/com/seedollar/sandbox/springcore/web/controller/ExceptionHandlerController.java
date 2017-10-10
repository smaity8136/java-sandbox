package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.web.exception.ExceptionHandlerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/exception-handler")
public class ExceptionHandlerController {

    @RequestMapping(path = "exception", method = RequestMethod.GET)
    public String showExceptionPage() {
        return "/exception-handler/exception";
    }

    @RequestMapping(path = "trigger", method = RequestMethod.GET)
    public String triggerException() {
        throw new ExceptionHandlerException();
    }

    @ExceptionHandler(ExceptionHandlerException.class)
    public String handleException() {
        return "/exception-handler/error";
    }


}
