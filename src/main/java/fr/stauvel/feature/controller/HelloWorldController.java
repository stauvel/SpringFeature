package fr.stauvel.feature.controller;

import fr.stauvel.feature.tools.Feature;
import fr.stauvel.feature.tools.FeatureUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("features");
        String profiles = System.getProperty("spring.profiles.active");
        model.addObject("profiles", profiles);
        model.addObject("managedFeatures", Feature.values());
        model.addObject("devMode", FeatureUtil.check(Feature.DEV_MODE));

        return model;
    }
}