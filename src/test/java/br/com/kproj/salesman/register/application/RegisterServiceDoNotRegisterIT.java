package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.Application;
import br.com.kproj.salesman.infrastructure.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RegisterServiceDoNotRegisterIT {

    @Autowired
    private RegisterService registerService;

    @Test
    public void shouldNotRegisterClient() {
        try {
            Individual individual = new Individual();
            individual.setName(null);
            registerService.register(individual);
            fail("should throw ConstraintViolationException");
        } catch (ConstraintViolationException e) {
            assertThat(e.getConstraintViolations().iterator().next().getMessage(), is("may not be null"));
        }
    }

    @Test
    public void shouldNotRegisterProject() {
        try {
            registerService.register(new Project());
            fail("should throw ConstraintViolationException");
        } catch (ConstraintViolationException e) {
            assertThat(e.getConstraintViolations().iterator().next().getMessage(), is("may not be null"));
        }
    }


}
