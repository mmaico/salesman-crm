package br.com.kproj.salesman.business_prospecting.campaigns.view;


import br.com.kproj.salesman.business_prospecting.campaigns.application.CampaignsApplication;
import br.com.kproj.salesman.business_prospecting.campaigns.infrastructure.CampaignsValidator;
import br.com.kproj.salesman.infrastructure.entity.campaigns.Campaign;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class CampaignsController {

    @Autowired
    private CampaignsApplication service;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private CampaignsValidator validator;

    @Autowired
    private SecurityHelper security;


    @InitBinder(value = "campaign")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }


    @RequestMapping(value = "/campaigns/add", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated Campaign campaign, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        if (campaign.isNew()) {
            //campaign.setCreatedBy(security.getPrincipal().getUser());
        }

        normalizeEntityRequest.doNestedReference(campaign);
        Campaign campaignLoad = service.save(campaign);

        return "/campaigns/" + campaignLoad.getId();
    }

    @RequestMapping(value = "/campaigns/add", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute Campaign campaign, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        if (campaign.isNew()) {
            throw new ValidationException(Sets.newHashSet("campaigns.update.without.id"));
        }

        normalizeEntityRequest.addFieldsToUpdate(campaign);
        Campaign campaignLoaded = service.save(campaign);

        return "/campaigns/" + campaignLoaded.getId();
    }
}
