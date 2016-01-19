package br.com.kproj.salesman.notifications.view;

import br.com.kproj.salesman.infrastructure.entity.notification.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.notifications.application.UserNotificationLogViewApplication;
import br.com.kproj.salesman.notifications.view.dto.ScheduleTriggerNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ScheduleTriggerNotificationController {



    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/schedule-trigger-notification/tasks/{taskId}", method = RequestMethod.POST)
    public @ResponseBody void createTriggerNotification(@ModelAttribute ScheduleTriggerNotificationDTO dto) {

    }



}
