package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.person.PersonProfile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class PersonRepositoryIT extends AbstractIntegrationTest {


    @Autowired
    private ProfileRepository repository;

    @Test
    public void shouldSaveProfile() {
        PersonProfile profile = new PersonProfile();
        profile.setName("Cliente Pessoa Fisica");
        profile.setId(5l);

        PersonProfile result = repository.save(profile);

        assertThat(result.getId(), is(5l));
    }
}