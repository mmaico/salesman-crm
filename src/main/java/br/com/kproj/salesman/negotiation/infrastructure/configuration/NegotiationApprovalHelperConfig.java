package br.com.kproj.salesman.negotiation.infrastructure.configuration;


import br.com.kproj.salesman.negotiation.proposal.approval.views.helpers.RequestApprovalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class NegotiationApprovalHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private RequestApprovalHelper requestApprovalHelper;


    @PostConstruct
    public void config() {

        resolver.getAttributesMap().put("requestApprovalHelper", requestApprovalHelper);
    }
}
