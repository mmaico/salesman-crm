package br.com.kproj.salesman.calendar.view;

import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class CalendarController {

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value="/calendar/view")
    public ModelAndView showCalendar() {

        return new ModelAndView("/calendar/detail");
    }

    @RequestMapping(value="/calendar/activities")
    public ModelAndView showActivitiesByRangeDate(@MatrixVariable(value = "startDate") Optional<String> startDate,
                                                  @MatrixVariable(value = "endDate") Optional<String> andDate) {


        return new ModelAndView("/calendar/detail");
    }
}
