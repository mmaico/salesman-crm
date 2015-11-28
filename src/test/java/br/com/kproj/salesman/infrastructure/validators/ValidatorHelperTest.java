package br.com.kproj.salesman.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorHelperTest {


    @Test
    public void shouldReturnErrors() {
        UserTest userTest = new UserTest();
        UserTestValidator validator = new UserTestValidator();
        Set<String> errors = null;
        try {
            ValidatorHelper.hasContraintViolated(userTest, validator);
        } catch(ValidationException validation) {
            errors = validation.getErrors();
        }

        assertThat(errors.size(), is(3));
        assertThat(errors.contains("usertest.name.is.null"), is(Boolean.TRUE));
        assertThat(errors.contains("usertest.lastname.is.null"), is(Boolean.TRUE));
        assertThat(errors.contains("usertest.birthdate.invalid"), is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenNotHaveErrors() {
        UserTest userTest = new UserTest();
        userTest.setBirthdate(new Date());
        userTest.setName("Name");
        userTest.setLastname("Lastname");

        UserTestValidator validator = new UserTestValidator();
        Set<String> errors = Sets.newHashSet();
        try {
            ValidatorHelper.hasContraintViolated(userTest, validator);
        } catch(ValidationException validation) {
            errors = validation.getErrors();
        }

        assertThat(errors.size(), is(0));
    }


    private class UserTestValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return UserTest.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            UserTest userTest = (UserTest) target;


            Set<ConstraintViolation<Object>> constraints = Validation.buildDefaultValidatorFactory().getValidator().validate(userTest);


            if (userTest.getBirthdate() == null) {
                errors.rejectValue("birthdate", "usertest.birthdate.invalid");
            }

            constraints.forEach(error ->
                    errors.rejectValue(error.getPropertyPath().toString(), error.getMessage()));


        }


    }

    private class UserTest {
        @NotNull(message = "usertest.name.is.null")
        private String name;
        @NotNull(message="usertest.lastname.is.null")
        private String lastname;
        private Date birthdate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Date birthdate) {
            this.birthdate = birthdate;
        }
    }
}