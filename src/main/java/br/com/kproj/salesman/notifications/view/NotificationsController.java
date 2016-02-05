package br.com.kproj.salesman.notifications.view;

import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.notifications.application.NotificationApplication;
import br.com.kproj.salesman.notifications.application.UserNotificationLogViewApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class NotificationsController {

    @Autowired
    private NotificationApplication application;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/notifications/proposals", method = RequestMethod.GET)
    public @ResponseBody void proposalNotification() {



    }





}
