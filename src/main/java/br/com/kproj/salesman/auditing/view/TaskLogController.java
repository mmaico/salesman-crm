package br.com.kproj.salesman.auditing.view;

import br.com.kproj.salesman.auditing.application.TaskAuditingApplication;
import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaskLogController {


    @Autowired
    private TaskAuditingApplication application;




    @RequestMapping(value = "/tasks/{taskId}/logs", method = RequestMethod.GET)
    public ModelAndView getListLogs(@PathVariable Long taskId,
                                    @PageableDefault(size = 1000) Pageable pageable, Model model) {

        Page<TaskAudinting> result = application.findLogs(taskId, Pager.binding(pageable));

        model.addAttribute("task", TaskBuilder.createTaskBuilder(taskId).build());
        model.addAttribute("taskLogs", result);
        return new ModelAndView("/delivery/delivery/logs/logs");

    }


}
