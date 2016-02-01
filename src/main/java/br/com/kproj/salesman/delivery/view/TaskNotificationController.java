package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.triggernotification.TaskNotificationApplication;
import br.com.kproj.salesman.delivery.view.dtos.TriggerNotificationDTO;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskNotificationBuilder;
import br.com.kproj.salesman.infrastructure.entity.notification.ScheduleTriggerNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaskNotificationController {

    @Autowired
    private TaskNotificationApplication application;


    @RequestMapping(value = "/tasks/{taskId}/trigger-notifications", method = RequestMethod.PUT)
    public  ModelAndView addTriggerNotification(@PathVariable Long taskId, @ModelAttribute TriggerNotificationDTO dto, Model model) {

        ScheduleTriggerNotification triggerNotification = TaskNotificationBuilder.createNotification().withDate(dto.getStart())
                .withTask(TaskBuilder.createTaskBuilder(taskId).build()).build();

        ScheduleTriggerNotification notification = application.register(triggerNotification);

        model.addAttribute("notification", notification);
        return new ModelAndView("/delivery/tasks/includes/task-notification");
    }


}
