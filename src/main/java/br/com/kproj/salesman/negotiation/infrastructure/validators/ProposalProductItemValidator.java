package br.com.kproj.salesman.negotiation.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.register.infrastructure.validators.EntityIDValidator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

@Component
public class ProposalProductItemValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    @Autowired
    private EntityIDValidator idValidator;


    @Override
    public boolean supports(Class<?> clazz) {
        return ProposalSaleableItem.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProposalSaleableItem productItem = (ProposalSaleableItem) target;

        validator.validate(productItem)
                .forEach(error -> errors.reject(error.getMessage()));

        idValidator.validate(productItem.getSaleableUnit(), errors);

        if (productItem.getPrice() != null
                && productItem.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            errors.reject("price", "domain.product.price.is.valid");
        }

    }

    @Override
    public void afterPropertiesSet(){
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}

    }
}
