package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.register.view.dto.LogActivityVO;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import br.com.kproj.salesman.timeline.infrastructure.TimelineActivitiesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;

@RestController
public class BusinessProposalTimelineController {

    @Autowired
    private TimelineActivitiesApplication service;

    @Autowired
    private TimelineApplication timelineApplication;

    @Autowired
    private TimelineActivitiesValidator validator;

    @Autowired
    private SecurityHelper security;



    @RequestMapping(value = "/business-proposal/{businessId}/logactivity/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity saveOfProposal(@PathVariable Long businessId, @ModelAttribute LogActivityVO logActivityVO,
                                                      BindingResult bindingResult) throws IOException {

        LogActivity logActivity = logActivityVO.getLogActivity();
        validator.validate(logActivity, new BindException(bindingResult));

        logActivity.setFiles(logActivityVO.getAppFiles());
        logActivity.setUser(security.getPrincipal().getUser());
        service.register(createBusinessProposal(businessId).build(), logActivity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/business-proposal/{businessId}/logactivity", method = RequestMethod.GET)
    public ModelAndView getTimelineContact(@PathVariable Long businessId, Model model,
                                           @RequestParam(defaultValue="edit",required=false, value="template") String templateName) {

        Timeline timeline = timelineApplication.register(createBusinessProposal(businessId).build());

        model.addAttribute(createBusinessProposal(businessId).build());
        model.addAttribute(timeline);
        return new ModelAndView("/timeline/proposal-timeline");
    }


}
