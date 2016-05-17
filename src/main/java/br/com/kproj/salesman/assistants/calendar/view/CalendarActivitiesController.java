package br.com.kproj.salesman.assistants.calendar.view;

import br.com.kproj.salesman.assistants.calendar.application.CalendarActivityApplication;
import br.com.kproj.salesman.assistants.calendar.application.dto.RangeDatesDTO;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Period;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@RestController
public class CalendarActivitiesController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private CalendarActivityApplication application;



    @RequestMapping(value="/calendar/calendar-activities")
    public ModelAndView showActivitiesByRangeDate(@MatrixVariable(value = "startDate") Optional<String> startDate,
                                                  @MatrixVariable(value = "endDate") Optional<String> endDate,
                                                  Model model) {

        RangeDatesDTO datesDTO = RangeDatesDTO.create(
                startDate.isPresent() ? startDate.get() : EMPTY,
                endDate.isPresent() ? endDate.get() : EMPTY);
        List<CalendarActivity> activities = null;

        if (datesDTO.hasRangeDate()) {
            activities = application.findByRangeDate(datesDTO);
        } else {
            activities = Lists.newArrayList(application.findAll(Pager.build().withPageSize(10000)));
        }

        model.addAttribute("activities", activities);
        return new ModelAndView("/calendar/calendar-activities");
    }


    @RequestMapping(value="/calendar/calendar-activities", method = RequestMethod.POST)
    public ModelAndView saveActivity(@ModelAttribute CalendarActivity activity, @ModelAttribute PeriodDTO periodDTO, Model model) {
        User user = security.getPrincipal().getUser();

        if (activity.getPeriod() == null) {
            activity.setPeriod(new Period());
        }
        activity.getPeriod().setStartDate(periodDTO.getFullStartDate());
        activity.getPeriod().setEndDate(periodDTO.getFullEndDate());
        activity.getPeriod().getFields().add("startDate");
        activity.getPeriod().getFields().add("endDate");
        activity.getPeriod().getFields().add("isAllDay");
        activity.getFields().add("period");

        CalendarActivity result = application.register(activity, user);

        model.addAttribute("activity", result);
        return new ModelAndView("/calendar/calendar-activity");
    }

    @RequestMapping(value="/calendar/calendar-activities/{activityId}", method = RequestMethod.GET)
    public ModelAndView saveActivity(@PathVariable Long activityId, Model model) {

        Optional<CalendarActivity> result = application.getOne(activityId);

        model.addAttribute("activity", result.isPresent() ? result.get() : null);
        return new ModelAndView("/calendar/edit");
    }
}
