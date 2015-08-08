package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.Application;
import br.com.kproj.salesman.infrastructure.entity.person.PersonProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PersonRepositoryTest {


    @Autowired
    private ProfileRepository repository;

    @Test
    public void shouldSaveProfile() {
        PersonProfile profile = new PersonProfile();
        profile.setName("Cliente Pessoa Fisica");
        profile.setId(1l);

        PersonProfile result = repository.save(profile);

        assertThat(result.getId(), is(1l));
    }
}