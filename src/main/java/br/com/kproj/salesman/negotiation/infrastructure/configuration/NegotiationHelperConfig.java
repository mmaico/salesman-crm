package br.com.kproj.salesman.negotiation.infrastructure.configuration;


import br.com.kproj.salesman.negotiation.view.helpers.ProposalSaleablesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class NegotiationHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private ProposalSaleablesHelper saleablesHelper;

    @PostConstruct
    public void config() {

        resolver.getAttributesMap().put("saleablesHelper", saleablesHelper);

    }
}
