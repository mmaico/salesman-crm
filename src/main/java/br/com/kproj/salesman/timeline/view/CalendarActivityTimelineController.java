package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infrastructure.entity.builders.CalendarActivityBuilder;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
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

import static br.com.kproj.salesman.infrastructure.entity.builders.CalendarActivityBuilder.createCalendarActivity;
import static br.com.kproj.salesman.infrastructure.entity.builders.ContactBuilder.createContact;

@RestController
public class CalendarActivityTimelineController {

    @Autowired
    private TimelineActivitiesApplication service;

    @Autowired
    private TimelineApplication timelineApplication;

    @Autowired
    private TimelineActivitiesValidator validator;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/calendar/calendar-activities/{activityId}/logactivity/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity saveOfCalendarActivity(@PathVariable Long activityId,
                                                               @ModelAttribute LogActivityVO logActivityVO,
                                                      BindingResult bindingResult) throws IOException {

        LogActivity logActivity = logActivityVO.getLogActivity();
        validator.validate(logActivity, new BindException(bindingResult));

        logActivity.setFiles(logActivityVO.getAppFiles());
        logActivity.setUser(security.getPrincipal().getUser());
        service.register(createCalendarActivity(activityId).build(), logActivity);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/calendar/calendar-activities/{activityId}/activities", method = RequestMethod.GET)
    public ModelAndView getTimelineContact(@PathVariable Long activityId, Model model) {

        Timeline timeline = timelineApplication.register(createCalendarActivity(activityId).build());

        model.addAttribute(createCalendarActivity(activityId).build());
        model.addAttribute(timeline);
        return new ModelAndView("/timeline/calendar-activity-timeline");
    }


}
