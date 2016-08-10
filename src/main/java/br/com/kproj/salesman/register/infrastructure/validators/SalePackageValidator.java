package br.com.kproj.salesman.register.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SalePackageValidator implements Validator {

    @Autowired
    private SaleableValidator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return SalePackageEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SalePackageEntity salePackage = (SalePackageEntity) target;

        validator.validate(salePackage, errors);

    }


}
