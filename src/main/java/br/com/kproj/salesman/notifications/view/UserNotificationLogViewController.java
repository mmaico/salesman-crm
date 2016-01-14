package br.com.kproj.salesman.notifications.view;

import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.notifications.application.UserNotificationLogViewApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserNotificationLogViewController {

    @Autowired
    private UserNotificationLogViewApplication application;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/notifications-view/proposals", method = RequestMethod.POST)
    public @ResponseBody void notificationViewProposal() {
        UserNotificationLogView userView = new UserNotificationLogView();
        userView.setUser(security.getPrincipal().getUser());
        userView.setLastVisualization(new Date());
        userView.setTypeLogView(UserNotificationLogView.TypeLogView.PROPOSAL_NOTIFICATION);

        application.register(userView);
    }

    @RequestMapping(value = "/notifications-view/task", method = RequestMethod.POST)
    public @ResponseBody void notificationViewTask() {
        UserNotificationLogView userView = new UserNotificationLogView();
        userView.setUser(security.getPrincipal().getUser());
        userView.setLastVisualization(new Date());
        userView.setTypeLogView(UserNotificationLogView.TypeLogView.TASK_NOTIFICATION);

        application.register(userView);
    }



}
