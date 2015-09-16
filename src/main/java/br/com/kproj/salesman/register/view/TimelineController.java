package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.register.infrastructure.validators.TimelineActivitiesValidator;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesService;
import br.com.kproj.salesman.timeline.application.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;
import static br.com.kproj.salesman.infrastructure.entity.builders.ContactBuilder.createContact;

@RestController
public class TimelineController {

    @Autowired
    private TimelineActivitiesService service;

    @Autowired
    private TimelineService timelineService;

    @Autowired
    private TimelineActivitiesValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @InitBinder(value = "logActivity")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/contact/{contactId}/logactivity/save", method = RequestMethod.POST)
    public ModelAndView saveOfContact(@PathVariable Long contactId, @ModelAttribute @Validated LogActivity logActivity,
                                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        Timeline timelineResult = service.register(createContact(contactId).build(), logActivity);

        model.addAttribute(timelineResult);
        return new ModelAndView("");
    }

    @RequestMapping(value = "/business-proposal/{businessId}/logactivity/save", method = RequestMethod.POST)
    public ModelAndView saveOfProposal(@PathVariable Long businessId, @ModelAttribute @Validated LogActivity logActivity,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        Timeline timelineResult = service.register(createBusinessProposal(businessId).build(), logActivity);

        model.addAttribute(timelineResult);
        return new ModelAndView("");
    }

    @RequestMapping(value = "/contact/{contactId}/logactivity", method = RequestMethod.GET)
    public ModelAndView getTimelineContact(@PathVariable Long contactId, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        Timeline timeline = timelineService.register(createContact(contactId).build());

        model.addAttribute(timeline);
        return new ModelAndView("");
    }


}
