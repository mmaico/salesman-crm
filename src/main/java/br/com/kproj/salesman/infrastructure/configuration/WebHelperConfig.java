package br.com.kproj.salesman.infrastructure.configuration;


import br.com.kproj.salesman.infrastructure.helpers.FormatMoneyHelper;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.register.infrastructure.helpers.ClientHelper;
import br.com.kproj.salesman.register.infrastructure.helpers.ProductHelper;
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


    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("clientHelper", clientHelper);
        resolver.getAttributesMap().put("productHelper", productHelper);
        resolver.getAttributesMap().put("moneyHelper", new FormatMoneyHelper());
        resolver.getAttributesMap().put("security", securityHelper);

    }
}
