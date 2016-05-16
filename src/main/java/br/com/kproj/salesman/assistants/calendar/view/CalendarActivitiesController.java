package br.com.kproj.salesman.assistants.calendar.view;

import br.com.kproj.salesman.assistants.calendar.application.CalendarActivityApplication;
import br.com.kproj.salesman.assistants.calendar.application.dto.RangeDatesDTO;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

        List<CalendarActivity> result = application.findByRangeDate(datesDTO);

        model.addAttribute("activities", result);
        return new ModelAndView("/calendar/calendar-activities");
    }

    @RequestMapping(value="/calendar/calendar-activities")
    public ModelAndView showAllActivities(Model model) {

        Page<CalendarActivity> activities = application.findAll(Pager.build().withPageSize(10000));

        model.addAttribute("activities", activities);
        return new ModelAndView("/calendar/calendar-activities");
    }

    @RequestMapping(value="/calendar/calendar-activities", method = RequestMethod.POST)
    public ModelAndView saveActivity(@ModelAttribute CalendarActivity activity, @ModelAttribute PeriodDTO periodDTO, Model model) {
        User user = security.getPrincipal().getUser();

        activity.getPeriod().setStartDate(periodDTO.getStartDate());
        activity.getPeriod().setEndDate(periodDTO.getEndDate());

        CalendarActivity result = application.register(activity, user);

        model.addAttribute("activity", result);
        return new ModelAndView("/calendar/calendar-activity");
    }
}
