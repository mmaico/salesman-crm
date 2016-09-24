package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import br.com.kproj.salesman.timeline.infrastructure.TimelineActivitiesValidator;
import br.com.kproj.salesman.timeline.view.dto.LogActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder.createTaskBuilder;

@RestController
public class TaskTimelineController {

    @Autowired
    private TimelineActivitiesApplication service;

    @Autowired
    private TimelineApplication timelineApplication;

    @Autowired
    private TimelineActivitiesValidator validator;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/tasks/{taskId}/logactivity/add", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity saveOfTask(@PathVariable Long taskId, @ModelAttribute LogActivityVO logActivityVO,
                                                      BindingResult bindingResult) throws IOException {

        LogActivity logActivity = logActivityVO.getLogActivity();
        validator.validate(logActivity, new BindException(bindingResult));

        logActivity.setFiles(logActivityVO.getAppFiles());
        //logActivity.setUser(security.getPrincipal().getUser());
        service.register(createTaskBuilder(taskId).build(), logActivity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{taskId}/activities", method = RequestMethod.GET)
    public ModelAndView getTimelineContact(@PathVariable Long taskId, Model model) {

        Timeline timeline = timelineApplication.register(createTaskBuilder(taskId).build());

        model.addAttribute(createTaskBuilder(taskId).build());
        model.addAttribute(timeline);
        return new ModelAndView("/timeline/task-timeline");
    }


}
