package br.com.kproj.salesman.assistants.activities.view;

import br.com.kproj.salesman.assistants.activities.application.PersonalActivityApplication;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.builders.PersonalActivityBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.PersonalActivityBuilder.createActivity;

@RestController
public class PersonalActivitiesController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private PersonalActivityApplication application;


    @RequestMapping(value = "/users/personal-activities/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute PersonalActivity activity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(activity);
        PersonalActivity activitySaved = application.register(activity);

        return "/users/personal-activities/" + activitySaved.getId();
    }

    @RequestMapping(value = "/users/personal-activities/save", method = RequestMethod.PUT)
    public  @ResponseBody String update(@ModelAttribute PersonalActivity activity) {

        normalizeEntityRequest.addFieldsToUpdate(activity);
        PersonalActivity activitySaved = application.register(activity);

        return "/users/personal-activities/" + activitySaved.getId();
    }

    @RequestMapping(value = "/users/personal-activities/new", method = RequestMethod.GET)
    public  ModelAndView newActivity() {

        return new ModelAndView("/users/activity/edit");
    }

    @RequestMapping(value = "/users/personal-activities/list", method = RequestMethod.GET)
    public  ModelAndView listActivity(Model model) {
        Iterable<PersonalActivity> result = application.findAll(Pager.build().withPageSize(1000));

        model.addAttribute("activities", result);
        return new ModelAndView("/users/activity/list");
    }

    @RequestMapping(value="/users/personal-activities/{activityId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="detail",required=false, value="template") String templateName,
                                 @PathVariable Long activityId, Model model) {

        Optional<PersonalActivity> result = this.application.getOne(activityId);

        model.addAttribute("activity", result.isPresent() ? result.get() : null);
        return new ModelAndView("/users/activity/" + templateName);
    }

    @RequestMapping(value = "/users/personal-activities/{parentActivityId}/subactivity", method = RequestMethod.POST)
    public  ModelAndView saveSubtask(@ModelAttribute PersonalActivity activity, @PathVariable("parentActivityId") Long parentActivityId, Model model) {

        normalizeEntityRequest.doNestedReference(activity);
        activity.setOwner(security.getPrincipal().getUser());
        application.registerSubtask(createActivity(parentActivityId).build(), activity);

        Optional<PersonalActivity> activityParentLoaded = application.getOne(parentActivityId);

        model.addAttribute("activity", activityParentLoaded.isPresent() ? activityParentLoaded.get() : null);
        return new ModelAndView("");
    }

    @RequestMapping(value="/users/personal-activities/{activityId}/change-status/{status}", method = RequestMethod.PUT)
    public @ResponseBody void signedTask(@PathVariable Long activityId, @PathVariable String status) {
        PersonalActivity activity = PersonalActivityBuilder.createActivity(activityId)
                .withStatus(PersonalAcvitityStatus.get(status)).build();

        User user = security.getPrincipal().getUser();

        application.changeStatus(activity, user);
    }


}
