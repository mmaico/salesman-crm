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
        final Client client = registerService.register(new Client("Client1 Client", new User("client1", "client1password")));
        assertThat(client, notNullValue());
        assertThat(client.getId(), notNullValue());
    }

    @Test
    public void shouldRegisterProject() {
        final Product product = registerService.register(new Project("project project1"));
        assertThat(product, notNullValue());
        assertThat(product.getId(), notNullValue());
    }

    @Test
    public void shouldRegisterVendor() {
        final Vendor vendor = registerService.register(new Vendor("Vendor1 Vendor", new User("vendor1", "vendor1password")));
        assertThat(vendor, notNullValue());
        assertThat(vendor.getId(), notNullValue());
    }

}
