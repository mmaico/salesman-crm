package br.com.kproj.salesman.register.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class DeveloperController {


    @RequestMapping(value="/developer")
    public ModelAndView developer(@RequestParam(value="template") String templateName) {

        return new ModelAndView(templateName);
    }
}
