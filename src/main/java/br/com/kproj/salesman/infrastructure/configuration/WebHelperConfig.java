package br.com.kproj.salesman.infrastructure.configuration;


import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.helpers.FormatMoneyHelper;
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
public class WebHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private ClientHelper clientHelper;

    @Autowired
    private ProductHelper productHelper;

    @Autowired
    private SecurityHelper securityHelper;

    @Autowired
    private TimelineImageHelper fileHelper;

    @Autowired
    private TimelineActivityHelper activityHelper;

    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("clientHelper", clientHelper);
        resolver.getAttributesMap().put("productHelper", productHelper);
        resolver.getAttributesMap().put("moneyHelper", new FormatMoneyHelper());
        resolver.getAttributesMap().put("security", securityHelper);
        resolver.getAttributesMap().put("timelineImageHelper", fileHelper);
        resolver.getAttributesMap().put("activityHelper", activityHelper);
        resolver.getAttributesMap().put("dateHelper", new DateHelper());

    }
}
