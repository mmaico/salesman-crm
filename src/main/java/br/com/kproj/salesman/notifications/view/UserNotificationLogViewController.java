package br.com.kproj.salesman.notifications.view;

import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogViewEntity;
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
        UserNotificationLogViewEntity userView = new UserNotificationLogViewEntity();
        //userView.setUser(security.getPrincipal().getUser());
        userView.setLastVisualization(new Date());
        userView.setTypeLogView(UserNotificationLogViewEntity.TypeLogView.PROPOSAL_NOTIFICATION);

        application.register(userView);
    }

    @RequestMapping(value = "/notifications-view/tasks", method = RequestMethod.POST)
    public @ResponseBody void notificationViewTask() {
        UserNotificationLogViewEntity userView = new UserNotificationLogViewEntity();
        //userView.setUser(security.getPrincipal().getUser());
        userView.setLastVisualization(new Date());
        userView.setTypeLogView(UserNotificationLogViewEntity.TypeLogView.TASK_NOTIFICATION);

        application.register(userView);
    }



}
