package br.com.kproj.salesman.strategy.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DashboardController {



    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dash() {

        return new ModelAndView("/dashboard/home");
    }



}
