package br.com.kproj.salesman.register.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @RequestMapping("/test")
    public ModelAndView home() {

        return new ModelAndView("index");
    }
}
