package br.com.kproj.salesman.notifications.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.notifications.application.NotificationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationHelper {

    @Autowired
    private NotificationApplication application;

    @Autowired
    private SecurityHelper security;

    public Long countTasksNotifications() {
        return application.findCountTaskNotificationBy(security.getPrincipal().getUser());
    }

    public Long countProposalsNotifications() {
        return application.findCountProposalBy(security.getPrincipal().getUser());
    }

}
