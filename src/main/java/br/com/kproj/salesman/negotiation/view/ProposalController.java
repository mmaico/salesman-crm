package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.negotiation.application.NegotiationService;
import br.com.kproj.salesman.negotiation.infrastructure.validators.BusinessProposalValidator;
import br.com.kproj.salesman.negotiation.view.dto.BusinessProposalDTO;
import br.com.kproj.salesman.register.application.contract.ClientService;
import br.com.kproj.salesman.register.application.contract.SaleableUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class ProposalController {

    @Autowired
    private NegotiationService service;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private BusinessProposalValidator validator;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SaleableUnitService saleableUnitService;

    @InitBinder(value = "proposalDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/proposals/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute @Validated BusinessProposalDTO proposalDTO,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        BusinessProposal businessProposal = proposalDTO.get();

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

    @RequestMapping(value="/proposals/persons/{idPerson}")
    public ModelAndView newProposal(Model model, @PathVariable Long idPerson) {

        Optional<Person> clientOptional  = clientService.getOne(idPerson);

        if (!clientOptional.isPresent()) {
            return new ModelAndView("redirect:/clients/list");
        }

        Iterable<SaleableUnit> saleable = saleableUnitService.findAll(Pager.build().withPageNumer(1).withPageSize(10000));

        model.addAttribute("saleables", saleable);
        model.addAttribute("client", clientOptional.get());
        return new ModelAndView("/clients/proposal/newProposal");
    }

}
