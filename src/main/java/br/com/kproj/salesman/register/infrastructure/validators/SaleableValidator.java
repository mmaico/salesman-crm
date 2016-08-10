package br.com.kproj.salesman.register.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class SaleableValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return SaleableUnitEntity.class.equals(clazz.getSuperclass());
    }

    @Override
    public void validate(Object target, Errors errors) {
    	SaleableUnitEntity saleableUnit = (SaleableUnitEntity) target;
        Set<ConstraintViolation<Object>> constraints = validator.validate(saleableUnit);
        
        if (saleableUnit.getActive()) {
        	if (saleableUnit.getPrice() == null) {
        		errors.rejectValue("price", "product.ative.without.price");
        	}
        }
        
        constraints.forEach(error ->
                errors.rejectValue(error.getPropertyPath().toString(), error.getMessage()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}
    }
}
