package br.com.kproj.salesman.notifications.infrastructure.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class NotificationHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;




    @PostConstruct
    public void config() {

        //resolver.getAttributesMap().put("saleablesHelper", saleablesHelper);


    }
}
