package fr.stauvel.feature.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("hello");
        model.addObject("msg", "hello world");
        model.addObject("profile", System.getProperty("spring.profiles.active"));

        return model;
    }
}