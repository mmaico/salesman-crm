package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.view.dto.BusinessProposalRequestMergeHelper;
import br.com.kproj.salesman.negotiation.view.dto.TemperatureDTO;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationBuilder.createNegotiation;
import static br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature.createChangeTemperature;
import static br.com.kproj.salesman.negotiation.domain.model.seller.SellerBuilder.createSeller;

@RestController
public class NegotiationController {

    @Autowired
    private NegotiationFacade service;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

//    @Autowired
//    private BusinessProposalValidator validator;

    @Autowired
    private ClientApplication clientApplication;

    @Autowired
    private SaleableApplication saleableApplication;

    @Autowired
    private ProposalSaleablesDTO proposalSaleablesDTO;

    @Autowired
    private SecurityHelper security;

//    @InitBinder(value = "businessProposal")
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @RequestMapping(value = "/proposals/save", method = RequestMethod.POST)
    public @ResponseBody
    Long save(@ModelAttribute @Validated Negotiation negotiation, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        BusinessProposalRequestMergeHelper.merge(proposalSaleablesDTO, negotiation);

        //normalizeEntityRequest.doNestedReference(negotiation);
        Optional<Negotiation> negotiationSaved = service.register(negotiation);

        return negotiationSaved.get().getId();
    }

    @RequestMapping(value = "/proposals/save", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute @Validated Negotiation negotiation,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        BusinessProposalRequestMergeHelper.merge(proposalSaleablesDTO, negotiation);

        //normalizeEntityRequest.addFieldsToUpdate(negotiation);
        negotiation.getFields().remove("paymentItems");
        service.register(negotiation);
    }

    @RequestMapping(value = "/proposals/{negotiationId}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Long negotiationId, @RequestParam(defaultValue="editProposal",required=false, value="template") String templateName, Model model) {

        Optional<Negotiation> result = service.getOne(negotiationId);

        proposalSaleablesDTO.clear();
        proposalSaleablesDTO.load(result.get().getSaleablesItems());
        proposalSaleablesDTO.setNegotiationId(negotiationId);

        Iterable<SaleableUnitEntity> saleable = saleableApplication.findAll(Pager.build().withPageNumer(1).withPageSize(10000));


        model.addAttribute("proposalSaleables", proposalSaleablesDTO);
        model.addAttribute("domain", result.get());
        //model.addAttribute("client", result.get().getClient());
        model.addAttribute("saleables", saleable);
        return new ModelAndView("/clients/domain/" + templateName);

    }

    @RequestMapping(value="/proposals/{negotiationId}/temperature", method = RequestMethod.PUT)
    public @ResponseBody void changeTemperature(@PathVariable Long negotiationId, @ModelAttribute TemperatureDTO temperatureDTO) {
        LoggedUser loggedUser = security.getPrincipal().getLoggedUser();

        Seller seller = createSeller(loggedUser.getId()).build();
        Negotiation negotiation = createNegotiation(negotiationId).build();
        NegotiationChangeTemperature negotiationChangeTemperature = createChangeTemperature(negotiation, temperatureDTO.getTemperature());

        service.changeTemperature(seller, negotiationChangeTemperature);
    }

    //Esse metodo esta no lugar errado
    @RequestMapping(value="/proposals/persons/{idPerson}")
    public ModelAndView newProposal(Model model, @PathVariable Long idPerson) {

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
        return new ModelAndView("/clients/domain/newProposal");
    }

}
