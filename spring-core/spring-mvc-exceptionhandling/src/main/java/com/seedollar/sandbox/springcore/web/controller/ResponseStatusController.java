package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.web.exception.ExceptionHandlerException;
import com.seedollar.sandbox.springcore.web.exception.ResponseStatusExampleException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("response-status")
public class ResponseStatusController {

    @RequestMapping(path = "/exception", method = RequestMethod.GET)
    public String showExceptionPage() {
        return "response-status/exception";
    }

    @RequestMapping(path = "/trigger", method = RequestMethod.GET)
    public String triggerException() {
        throw new ResponseStatusExampleException();
    }
}
