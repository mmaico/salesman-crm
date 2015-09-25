package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.builders.AppFileBuilder;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.register.infrastructure.validators.TimelineActivitiesValidator;
import br.com.kproj.salesman.register.view.dto.LogActivityVO;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesService;
import br.com.kproj.salesman.timeline.application.TimelineService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Autowired
    private SecurityHelper security;



    @RequestMapping(value = "/contact/{contactId}/logactivity/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity saveOfContact(@PathVariable Long contactId, @ModelAttribute LogActivityVO logActivityVO,
                                                      BindingResult bindingResult, Model model) throws IOException {

        LogActivity logActivity = logActivityVO.getLogActivity();
        validator.validate(logActivity, new BindException(bindingResult));

        logActivity.setFiles(logActivityVO.getAppFiles());
        logActivity.setUser(security.getPrincipal().getUser());
        Timeline timelineResult = service.register(createContact(contactId).build(), logActivity);

        model.addAttribute(timelineResult);
        return new ResponseEntity<>(HttpStatus.OK);
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
    public ModelAndView getTimelineContact(@PathVariable Long contactId, Model model,
                                           @RequestParam(defaultValue="edit",required=false, value="template") String templateName) {

        Timeline timeline = timelineService.register(createContact(contactId).build());

        model.addAttribute(timeline);
        return new ModelAndView("/timeline/timeline");
    }


    @RequestMapping("/timeline/{activityId}/file/{fileId}")
    public void getImage(HttpServletResponse response,
                         @PathVariable Long activityId, @PathVariable Long fileId) throws IOException {

        LogActivity activity = new LogActivity(activityId);
        AppFile file = AppFileBuilder.create(fileId).build();

        byte[] activityFile = this.service.getActivityFile(activity, file);

        IOUtils.write(activityFile, response.getOutputStream());
    }

}
