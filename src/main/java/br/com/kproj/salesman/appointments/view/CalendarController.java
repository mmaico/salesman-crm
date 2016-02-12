package br.com.kproj.salesman.appointments.view;

import br.com.kproj.salesman.appointments.application.CalendarActivityApplication;
import br.com.kproj.salesman.appointments.application.dto.RangeDatesDTO;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@RestController
public class CalendarController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private CalendarActivityApplication application;


    @RequestMapping(value="/calendar/view")
    public ModelAndView showCalendar() {

        return new ModelAndView("/calendar/detail");
    }

    @RequestMapping(value="/calendar/calendar-activities")
    public ModelAndView showActivitiesByRangeDate(@MatrixVariable(value = "startDate") Optional<String> startDate,
                                                  @MatrixVariable(value = "endDate") Optional<String> endDate,
                                                  Model model) {

        RangeDatesDTO datesDTO = RangeDatesDTO.create(
                startDate.isPresent() ? startDate.get() : EMPTY,
                endDate.isPresent() ? endDate.get() : EMPTY);

        List<CalendarActivity> result = application.findByRangeDate(datesDTO);

        model.addAttribute("activities", result);
        return new ModelAndView("/calendar/detail");
    }

    @RequestMapping(value="/calendar/calendar-activities", method = RequestMethod.POST)
    public @ResponseBody void saveActivity(@ModelAttribute CalendarActivity activity) {
        User user = security.getPrincipal().getUser();
        application.register(activity, user);
    }
}
