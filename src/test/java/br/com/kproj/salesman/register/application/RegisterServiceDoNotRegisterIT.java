package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.Application;
import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.entity.Project;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.Vendor;
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
            registerService.register(new Client(null, new User("client1", "client1password")));
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

    @Test
    public void shouldNotRegisterVendor() {
        try {
            registerService.register(new Vendor(null, new User("vendor1", "vendor1password")));
            fail("should throw ConstraintViolationException");
        } catch (ConstraintViolationException e) {
            assertThat(e.getConstraintViolations().iterator().next().getMessage(), is("may not be null"));
        }
    }


}
