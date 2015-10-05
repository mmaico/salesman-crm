package br.com.kproj.salesman.register.infrastructure.validators;

import br.com.kproj.salesman.register.view.dto.UserVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class UserValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;
    
    @Autowired
    private AvatarValidator avatarValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserVO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	UserVO user = (UserVO) target;
        Set<ConstraintViolation<Object>> constraints = validator.validate(user);

        if (user.isNew()) {
            if (isBlank(user.getPassword()) || isBlank(user.getPasswordConfirm())
                    || !user.getPassword().equals(user.getPasswordConfirm()))
            errors.rejectValue("password", "Senha invalida");
        }
        else {
            constraints = constraints.stream()
                    .filter(e ->
                            !e.getMessageTemplate().equals("user.password")
                            && !e.getMessageTemplate().equals("user.login"))
                    .collect(Collectors.toSet());
        }
        
        avatarValidator.validate(user.getAvatarFile(), errors);
        
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
