package br.com.kproj.salesman.auditing.infrastructure;


import br.com.kproj.salesman.negotiation.view.helpers.BusinessProposalHelper;
import br.com.kproj.salesman.negotiation.view.helpers.ProposalSaleablesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class AuditingHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private ProposalSaleablesHelper saleablesHelper;

    @Autowired
    private BusinessProposalHelper businessProposalHelper;

    @PostConstruct
    public void config() {

        resolver.getAttributesMap().put("saleablesHelper", saleablesHelper);
        resolver.getAttributesMap().put("businessProposalHelper", businessProposalHelper);

    }
}
