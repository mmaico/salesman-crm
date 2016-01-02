package br.com.kproj.salesman.negotiation.infrastructure.validators;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.view.dto.PaymentDefinitionDTO;
import br.com.kproj.salesman.negotiation.view.helpers.ProposalSaleablesHelper;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class PaymentDefinitionDTOValidate implements Validator {

    @Autowired
    private ProposalSaleablesHelper proposalSaleablesHelper;

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentDefinitionDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PaymentDefinitionDTO item = (PaymentDefinitionDTO) target;

        if (proposalSaleablesHelper.getTotalItems().equals(BigDecimal.ZERO)) {
            throw new ValidationException(Sets.newHashSet("proposal.necessary.products.to.generate.payment"));
        }

        if (item.getInstallments() < 1) {
            throw new ValidationException(Sets.newHashSet("proposal.payment.invalid.quantity.installments"));
        }

    }
}
