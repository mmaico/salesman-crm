package br.com.kproj.salesman.assistants.activities.view;

import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PersonalActivitiesController {

    @Autowired
    private SecurityHelper security;



    @RequestMapping(value="/personal-activities/view")
    public ModelAndView showCalendar() {

        return new ModelAndView("/calendar/detail");
    }

    @RequestMapping(value="/personal-activities/save")
    public ModelAndView save() {

        return new ModelAndView("/calendar/detail");
    }


}
