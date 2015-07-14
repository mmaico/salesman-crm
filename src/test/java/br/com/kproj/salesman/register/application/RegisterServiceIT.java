package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.Application;
import br.com.kproj.salesman.infrastructure.entity.*;
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
        company.setUser(new User("client1", "client1password"));
        company.setName("Client1 Client");
        final Client client = registerService.register(company);
        assertThat(client, notNullValue());
        assertThat(client.getId(), notNullValue());
    }

    @Test
    public void shouldRegisterProject() {
        final Product product = registerService.register(new Project("project project1"));
        assertThat(product, notNullValue());
        assertThat(product.getId(), notNullValue());
    }

}
