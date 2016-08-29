package br.com.kproj.salesman.negotiation.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.register.infrastructure.validators.EntityIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Component
public class BusinessProposalValidator implements Validator {

    @Autowired
    private EntityIDValidator idValidator;


    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessProposalEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BusinessProposalEntity businessProposalEntity = (BusinessProposalEntity) target;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        factory.getValidator().validate(businessProposalEntity)
                .forEach(error -> errors.reject(error.getMessage()));

        idValidator.validate(businessProposalEntity.getClient(), errors);
        idValidator.validate(businessProposalEntity.getSeller(), errors);
    }

}
