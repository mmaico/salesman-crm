package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.negotiation.application.NegotiationService;
import br.com.kproj.salesman.negotiation.infrastructure.validators.BusinessProposalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProposalController {

    @Autowired
    private NegotiationService service;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private BusinessProposalValidator validator;

    @InitBinder(value = "businessProposal")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/proposals/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute @Validated BusinessProposal businessProposal,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.doNestedReference(businessProposal);
        BusinessProposal newBusinessProposal = service.register(businessProposal);

        model.addAttribute("proposal", newBusinessProposal);
        return new ModelAndView("proposal");
    }

    @RequestMapping(value = "/proposals/save", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute @Validated BusinessProposal businessProposal,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(businessProposal);
        BusinessProposal newBusinessProposal = service.register(businessProposal);

        model.addAttribute("proposal", newBusinessProposal);
        return new ModelAndView("proposal");
    }


}
