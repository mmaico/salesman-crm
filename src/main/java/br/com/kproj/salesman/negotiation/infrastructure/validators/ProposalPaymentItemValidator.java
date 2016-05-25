package br.com.kproj.salesman.negotiation.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalPaymentItem;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class ProposalPaymentItemValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    private Date yesterday = Date.from(Instant.now().minus(1 , ChronoUnit.DAYS));


    @Override
    public boolean supports(Class<?> clazz) {
        return ProposalPaymentItem.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProposalPaymentItem paymentItem = (ProposalPaymentItem) target;

        validator.validate(paymentItem)
                .forEach(error -> errors.reject(error.getMessage()));

        if (paymentItem.getValue() != null
                && paymentItem.getValue().compareTo(BigDecimal.ZERO) > 0) {
            errors.reject("price", "domain.payment.value.is.valid");
        }

        if (paymentItem.getDueDate() != null
                && !paymentItem.getDueDate().after(yesterday)) {
            errors.reject("date", "domain.payment.date.is.valid");
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
