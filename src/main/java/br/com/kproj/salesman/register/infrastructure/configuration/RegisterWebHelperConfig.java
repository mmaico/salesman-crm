package br.com.kproj.salesman.register.infrastructure.configuration;


import br.com.kproj.salesman.delivery.infrastructure.helpers.TaskTemplateHelpers;
import br.com.kproj.salesman.register.infrastructure.helpers.RegionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class RegisterWebHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private RegionHelper regionHelper;


    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("regionHelper", regionHelper);
    }

}
