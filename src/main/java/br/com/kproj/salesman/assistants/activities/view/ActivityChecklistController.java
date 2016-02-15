package br.com.kproj.salesman.assistants.activities.view;

import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ActivityChecklistController {

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value="/personal-activities/checklist")
    public ModelAndView showActivitiesByRangeDate() {


        return new ModelAndView("/calendar/detail");
    }


}
