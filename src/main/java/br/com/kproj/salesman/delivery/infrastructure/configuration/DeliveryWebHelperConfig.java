package br.com.kproj.salesman.delivery.infrastructure.configuration;


import br.com.kproj.salesman.delivery.infrastructure.helpers.DeliveryDashboardHelper;
import br.com.kproj.salesman.delivery.infrastructure.helpers.TaskHelper;
import br.com.kproj.salesman.delivery.infrastructure.helpers.TaskTemplateHelpers;
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

    @Autowired
    private DeliveryDashboardHelper deliveryDashboardHelper;

    @Autowired
    private TaskHelper taskHelper;


    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("taskTemplateHelper", taskTemplateHelpers);
        resolver.getAttributesMap().put("deliveryDashboardHelper", deliveryDashboardHelper);
        resolver.getAttributesMap().put("taskHelper", taskHelper);
    }
}
