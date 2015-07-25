package br.com.kproj.salesman.negotiation.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.register.infrastructure.validators.EntityIDValidator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Component
public class BusinessProposalValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    @Autowired
    private EntityIDValidator idValidator;


    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessProposal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BusinessProposal businessProposal = (BusinessProposal) target;

        validator.validate(businessProposal)
                .forEach(error -> errors.reject(error.getMessage()));

        idValidator.validate(businessProposal.getPerson(), errors);
        idValidator.validate(businessProposal.getVendor(), errors);
    }

    @Override
    public void afterPropertiesSet(){
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}

    }
}
