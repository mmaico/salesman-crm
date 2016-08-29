package br.com.kproj.salesman.negotiationold.proposal.view;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiationold.proposal.application.NegotiationApplication;
import br.com.kproj.salesman.negotiationold.infrastructure.validators.BusinessProposalValidator;
import br.com.kproj.salesman.negotiationold.proposal.view.dto.BusinessProposalRequestMergeHelper;
import br.com.kproj.salesman.negotiationold.proposal.view.dto.TemperatureDTO;
import br.com.kproj.salesman.negotiationold.proposal.view.dto.session.ProposalSaleablesDTO;
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

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;

@RestController
public class BusinessProposalController {

//    @Autowired
//    private NegotiationApplication service;
//
//    @Autowired
//    private NormalizeEntityRequest normalizeEntityRequest;
//
//    @Autowired
//    private BusinessProposalValidator validator;
//
//
//
//    @Autowired
//    private ClientApplication clientApplication;
//
//    @Autowired
//    private SaleableApplication saleableApplication;
//
//    @Autowired
//    private ProposalSaleablesDTO proposalSaleablesDTO;
//
//    @Autowired
//    private SecurityHelper security;
//
//    @InitBinder(value = "businessProposal")
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }
//
//    @RequestMapping(value = "/proposals/save", method = RequestMethod.POST)
//    public @ResponseBody
//    Long save(@ModelAttribute @Validated BusinessProposalEntity businessProposalEntity, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(bindingResult.getAllErrors());
//        }
//
//        BusinessProposalRequestMergeHelper.merge(proposalSaleablesDTO, businessProposalEntity);
//
//        normalizeEntityRequest.doNestedReference(businessProposalEntity);
//        BusinessProposalEntity register = service.register(businessProposalEntity);
//
//        return register.getId();
//    }
//
//    @RequestMapping(value = "/proposals/save", method = RequestMethod.PUT)
//    public @ResponseBody void update(@ModelAttribute @Validated BusinessProposalEntity businessProposalEntity,
//                             BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(bindingResult.getAllErrors());
//        }
//
//        BusinessProposalRequestMergeHelper.merge(proposalSaleablesDTO, businessProposalEntity);
//
//        normalizeEntityRequest.addFieldsToUpdate(businessProposalEntity);
//        businessProposalEntity.getFields().remove("paymentItems");
//        service.register(businessProposalEntity);
//    }
//
//    @RequestMapping(value = "/proposals/{proposalId}", method = RequestMethod.GET)
//    public ModelAndView get(@PathVariable Long proposalId, @RequestParam(defaultValue="editProposal",required=false, value="template") String templateName, Model model) {
//
//        Optional<BusinessProposalEntity> result = service.getOne(proposalId);
//
//        proposalSaleablesDTO.clear();
//        proposalSaleablesDTO.load(result.get().getSaleableItems());
//        proposalSaleablesDTO.setNegotiationId(proposalId);
//
//        Iterable<SaleableUnitEntity> saleable = saleableApplication.findAll(Pager.build().withPageNumer(1).withPageSize(10000));
//
//
//        model.addAttribute("proposalSaleables", proposalSaleablesDTO);
//        model.addAttribute("domain", result.get());
//        //model.addAttribute("client", result.get().getClient());
//        model.addAttribute("saleables", saleable);
//        return new ModelAndView("/clients/domain/" + templateName);
//
//    }
//
//    @RequestMapping(value="/proposals/{proposalId}/temperature", method = RequestMethod.PUT)
//    public @ResponseBody void changeTemperature(@PathVariable Long proposalId, @ModelAttribute TemperatureDTO temperatureDTO) {
//        UserEntity user = security.getPrincipal().getUser();
//        service.changeTemperature(createBusinessProposal(proposalId).build(), user, temperatureDTO.getTemperature());
//    }
//
//
//    @RequestMapping(value="/proposals/persons/{idPerson}")
//    public ModelAndView newProposal(Model model, @PathVariable Long idPerson) {
//
//        Optional<Person> clientOptional  = clientApplication.getOne(idPerson);
//
//        if (!clientOptional.isPresent()) {
//            return new ModelAndView("redirect:/clients/list");
//        }
//
//        Iterable<SaleableUnitEntity> saleable = saleableApplication.findAll(Pager.build().withPageNumer(1).withPageSize(10000));
//
//        proposalSaleablesDTO.clear();
//
//        model.addAttribute("saleables", saleable);
//        model.addAttribute("client", clientOptional.get());
//        return new ModelAndView("/clients/domain/newProposal");
//    }

}
