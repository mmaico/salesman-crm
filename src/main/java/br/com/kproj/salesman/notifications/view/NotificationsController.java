package br.com.kproj.salesman.notifications.view;

import br.com.kproj.salesman.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotification;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.notifications.application.NotificationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class NotificationsController {

    @Autowired
    private NotificationApplication application;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/notifications/proposals", method = RequestMethod.GET)
    public ModelAndView proposalNotification(Model model) {

        List<ApprovalBusinessProposalNotification> result = application.findProposalByUser(null);

        model.addAttribute("proposalNotifications", result);
        return new ModelAndView("/assets/includes/vm/notifications/domain-notification");
    }

    @RequestMapping(value = "/notifications/tasks", method = RequestMethod.GET)
    public ModelAndView tasksNotification(Model model) {

        List<TaskNotification> result = application.findTaskNotificationByUser(null);

        model.addAttribute("tasksNotifications", result);
        return new ModelAndView("/assets/includes/vm/notifications/task-notification");
    }




}
