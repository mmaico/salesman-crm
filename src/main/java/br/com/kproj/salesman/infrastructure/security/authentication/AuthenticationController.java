package br.com.kproj.salesman.infrastructure.security.authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthenticationController {

    @RequestMapping(value= {"/login", "/"})
    public ModelAndView login() {

        return new ModelAndView("login");
    }

}
