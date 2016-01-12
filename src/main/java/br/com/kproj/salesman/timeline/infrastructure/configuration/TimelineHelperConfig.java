package br.com.kproj.salesman.timeline.infrastructure.configuration;


import br.com.kproj.salesman.timeline.view.helpers.TimelineHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class TimelineHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private TimelineHelper timelineHelper;


    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("timelineHelper", timelineHelper);
    }
}
