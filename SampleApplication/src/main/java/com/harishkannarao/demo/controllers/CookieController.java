package com.harishkannarao.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CookieController {

    @RequestMapping(value = "/viewCookie", method = RequestMethod.GET)
    public ModelAndView viewCookie(@CookieValue(value = "my-cookie") Optional<String> myCookie) {
        ModelMap model = new ModelMap();
        myCookie.ifPresent(s -> model.put("cookieValue", s));
        return new ModelAndView("viewCookie", model);
    }
}
