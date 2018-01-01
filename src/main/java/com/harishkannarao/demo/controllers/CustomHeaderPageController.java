package com.harishkannarao.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static java.util.Optional.ofNullable;

@Controller
public class CustomHeaderPageController {

    @RequestMapping(value = "/showCustomHeader", method = RequestMethod.GET)
    public ModelAndView displayCutomHeaderPage(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        String customHeader = ofNullable(request.getHeader("my-custom-header"))
                .orElse("No Custom Header");
        model.put("customHeaderValue", customHeader);
        return new ModelAndView("customHeader", model);
    }
}
