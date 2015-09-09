package br.com.kproj.salesman.register.infrastructure.helpers;


import br.com.kproj.salesman.infrastructure.helpers.FormatMoneyHelper;
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

    @Autowired
    private ProductHelper productHelper;



    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("clientHelper", clientHelper);
        resolver.getAttributesMap().put("productHelper", productHelper);
        resolver.getAttributesMap().put("moneyHelper", new FormatMoneyHelper());

    }
}
