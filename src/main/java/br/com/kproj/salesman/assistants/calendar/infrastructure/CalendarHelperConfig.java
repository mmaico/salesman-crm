package br.com.kproj.salesman.assistants.calendar.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class CalendarHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private ActivityTypeHelper activityTypeHelper;

    @PostConstruct
    public void config() {

        resolver.getAttributesMap().put("activityTypeHelper", activityTypeHelper);

    }
}
