package br.com.kproj.salesman.delivery.infrastructure.configuration;


import br.com.kproj.salesman.delivery.application.TaskTemplateApplication;
import br.com.kproj.salesman.delivery.infrastructure.helpers.TaskTemplateHelpers;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.helpers.FormatMoneyHelper;
import br.com.kproj.salesman.infrastructure.helpers.LocationHelper;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.register.infrastructure.helpers.ClientHelper;
import br.com.kproj.salesman.register.infrastructure.helpers.ProductHelper;
import br.com.kproj.salesman.register.infrastructure.helpers.TimelineActivityHelper;
import br.com.kproj.salesman.register.infrastructure.helpers.TimelineImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class DeliveryWebHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private TaskTemplateHelpers taskTemplateHelpers;


    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("taskTemplateHelper", taskTemplateHelpers);
    }
}
