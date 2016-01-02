package br.com.kproj.salesman.negotiation.infrastructure.configuration;


import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.helpers.FormatMoneyHelper;
import br.com.kproj.salesman.infrastructure.helpers.LocationHelper;
import br.com.kproj.salesman.infrastructure.helpers.MessagesI18nHelper;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class NegotiationHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private SecurityHelper securityHelper;

    @Autowired
    private LocationHelper locationHelper;

    @Autowired
    private MessagesI18nHelper messagesI18nHelper;

    @PostConstruct
    public void config() {

        resolver.getAttributesMap().put("moneyHelper", new FormatMoneyHelper());
        resolver.getAttributesMap().put("security", securityHelper);
        resolver.getAttributesMap().put("dateHelper", new DateHelper());
        resolver.getAttributesMap().put("locationHelper", locationHelper);
        resolver.getAttributesMap().put("messagei18n", messagesI18nHelper);

    }
}
