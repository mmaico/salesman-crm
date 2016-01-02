package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder;
import br.com.kproj.salesman.negotiation.infrastructure.validators.PaymentDefinitionDTOValidate;
import br.com.kproj.salesman.negotiation.view.dto.PaymentDefinitionDTO;
import br.com.kproj.salesman.negotiation.view.dto.UpdatePackageItemsDTO;
import br.com.kproj.salesman.negotiation.view.dto.UpdateQuantityPriceItemsDTO;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleableItemDTO;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.negotiation.view.helpers.PaymentDetailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class PrepareInstallmentsController {


    @Autowired
    private PaymentDetailHelper helper;

    @Autowired
    private PaymentDefinitionDTOValidate validator;


    @RequestMapping(value="/proposals/installments", method = RequestMethod.GET)
    public ModelAndView addItem(@RequestParam(value = "installments") Integer installments,
                                @RequestParam(value = "firstDueDate", required = false) Date firstDueDate,
                                @RequestParam(value = "interval", required = false) Integer interval, Model model) {


        PaymentDefinitionDTO result = PaymentDefinitionDTO.create(installments, firstDueDate, interval);
        hasContraintViolated(result, validator);


        model.addAttribute("payment", helper.generate(result));
        return new ModelAndView("/clients/proposal/installments");
    }


}
