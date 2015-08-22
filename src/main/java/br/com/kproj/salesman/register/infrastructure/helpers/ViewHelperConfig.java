package br.com.kproj.salesman.register.infrastructure.helpers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class ViewHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private ClientHelper clientHelper;


    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("clientHelper", clientHelper);
    }
}
