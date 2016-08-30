package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.application.NegotiationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NegotiationController {

    @Autowired
    private NegotiationFacade service;

    @Autowired
    private SecurityHelper security;



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
