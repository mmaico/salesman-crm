package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.negotiation.application.NegotiationApplication;
import br.com.kproj.salesman.negotiation.infrastructure.validators.BusinessProposalValidator;
import br.com.kproj.salesman.negotiation.view.dto.BusinessProposalRequestMergeHelper;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class BusinessProposalController {

    @Autowired
    private NegotiationApplication service;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private BusinessProposalValidator validator;

    @Autowired
    private ClientApplication clientApplication;

    @Autowired
    private SaleableApplication saleableApplication;

    @Autowired
    private ProposalSaleablesDTO proposalSaleablesDTO;

    @InitBinder(value = "businessProposal")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/proposals/save", method = RequestMethod.POST)
    public @ResponseBody
    BusinessProposal save(@ModelAttribute @Validated BusinessProposal businessProposal, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        BusinessProposalRequestMergeHelper.merge(proposalSaleablesDTO, businessProposal);

        normalizeEntityRequest.doNestedReference(businessProposal);
        return service.register(businessProposal);

    }

    @RequestMapping(value = "/proposals/save", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute @Validated BusinessProposal businessProposal,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        BusinessProposalRequestMergeHelper.merge(proposalSaleablesDTO, businessProposal);

        normalizeEntityRequest.addFieldsToUpdate(businessProposal);
        businessProposal.getFields().remove("paymentItems");
        service.register(businessProposal);
    }

    @RequestMapping(value = "/proposals/{proposalId}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Long proposalId, Model model) {

        Optional<BusinessProposal> result = service.getOne(proposalId);

        proposalSaleablesDTO.clear();
        proposalSaleablesDTO.load(result.get().getSaleableItems());

        Iterable<SaleableUnit> saleable = saleableApplication.findAll(Pager.build().withPageNumer(1).withPageSize(10000));


        model.addAttribute("proposalSaleables", proposalSaleablesDTO);
        model.addAttribute("proposal", result.get());
        model.addAttribute("client", result.get().getClient());
        model.addAttribute("saleables", saleable);
        return new ModelAndView("/clients/proposal/editProposal");

    }

    @RequestMapping(value="/proposals/persons/{idPerson}")
    public ModelAndView newProposal(Model model, @PathVariable Long idPerson) {

        Optional<Person> clientOptional  = clientApplication.getOne(idPerson);

        if (!clientOptional.isPresent()) {
            return new ModelAndView("redirect:/clients/list");
        }

        Iterable<SaleableUnit> saleable = saleableApplication.findAll(Pager.build().withPageNumer(1).withPageSize(10000));

        proposalSaleablesDTO.clear();

        model.addAttribute("saleables", saleable);
        model.addAttribute("client", clientOptional.get());
        return new ModelAndView("/clients/proposal/newProposal");
    }

}
