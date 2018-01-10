package com.harishkannarao.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThymeleafAngularController {

    @RequestMapping(value = "/thymeleafAngularDemo", method = RequestMethod.GET)
    public ModelAndView displayPage() {
        ModelMap model = new ModelMap();
        model.addAttribute("defaultFirst", "4");
        model.addAttribute("defaultSecond", "2");
        return new ModelAndView("thymeleafAngularDemo", model);
    }
}
