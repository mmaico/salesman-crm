package br.com.kproj.salesman.notifications.view;

import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotificationView;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.notifications.application.ProposalNotificationViewApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskNotificationViewController {

    @Autowired
    private TaskNotificationViewRepository application;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/notifications/tasks", method = RequestMethod.PUT)
    public @ResponseBody void save() {
        TaskNotificationView userView = new TaskNotificationView();
        userView.setUser(security.getPrincipal().getUser());

        //application.saveUserView(userView);
    }



}
