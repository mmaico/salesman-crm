package br.com.kproj.salesman.register.infrastructure.configuration;


import br.com.kproj.salesman.delivery.infrastructure.helpers.TaskTemplateHelpers;
import br.com.kproj.salesman.register.infrastructure.helpers.*;
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
    @Autowired
    private SellersHelper sellersHelper;

    @Autowired
    private ClientHelper clientHelper;

    @Autowired
    private ProductHelper productHelper;

    @Autowired
    private TimelineImageHelper fileHelper;

    @Autowired
    private TimelineActivityHelper activityHelper;


    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("regionHelper", regionHelper);
        resolver.getAttributesMap().put("sellersHelper", sellersHelper);
        resolver.getAttributesMap().put("clientHelper", clientHelper);
        resolver.getAttributesMap().put("productHelper", productHelper);
        resolver.getAttributesMap().put("timelineImageHelper", fileHelper);
        resolver.getAttributesMap().put("activityHelper", activityHelper);
    }

}
