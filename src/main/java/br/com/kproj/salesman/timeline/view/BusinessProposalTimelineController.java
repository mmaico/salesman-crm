package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.BusinessProposalApprovalActivity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import br.com.kproj.salesman.timeline.infrastructure.TimelineActivitiesValidator;
import br.com.kproj.salesman.timeline.view.dto.BusinessProposalApprovalActivityVO;
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

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;
import static br.com.kproj.salesman.infrastructure.entity.builders.ContactBuilder.createContact;

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
        //logActivity.setUser(security.getPrincipal().getUser());
        service.register(createBusinessProposal(businessId).build(), logActivity);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/business-proposal/{businessId}/approval-activity/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity saveOfProposal(@PathVariable Long businessId, @ModelAttribute BusinessProposalApprovalActivityVO approvalActivityVO,
                                                      BindingResult bindingResult) throws IOException {

        BusinessProposalApprovalActivity activity = approvalActivityVO.getLogActivity();

        validator.validate(activity, new BindException(bindingResult));

        activity.setFiles(approvalActivityVO.getAppFiles());
        //activity.setUser(security.getPrincipal().getUser());
        service.register(createBusinessProposal(businessId).build(), activity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/business-proposal/{businessId}/activities", method = RequestMethod.GET)
    public ModelAndView getTimelineBusinessProposal(@PathVariable Long businessId, Model model) {

        Timeline timeline = timelineApplication.register(createBusinessProposal(businessId).build());

        model.addAttribute(createBusinessProposal(businessId).build());
        model.addAttribute(timeline);
        return new ModelAndView("/timeline/domain-timeline");
    }


}
