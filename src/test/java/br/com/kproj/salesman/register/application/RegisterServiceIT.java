package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.Application;
import br.com.kproj.salesman.infrastructure.entity.person.Company;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RegisterServiceIT {

    @Autowired
    private RegisterService registerService;

    @Test
    public void shouldRegisterClient() {
        Company company = new Company();
        company.setName("Client1 Client");
        final Person client = registerService.register(company);
        assertThat(client, notNullValue());
        assertThat(client.getId(), notNullValue());
    }

}
