package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProviderDomainServiceImplTest {


    @InjectMocks
    private ProviderDomainServiceImpl service;


    @Test
    public void shouldNotReturnErrorWhenProfileIs1() {
        Person person = new Person();
        person.setProfile(PersonProfilesEnum.INDIVIDUAL_PROVIDER.get());


        service.verifyPreconditionToSave(person);

    }

    @Test
    public void shouldNotReturnErrorWhenProfileIs2() {
        Person person = new Person();
        person.setProfile(PersonProfilesEnum.COMPANY_PROVIDER.get());


        service.verifyPreconditionToSave(person);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnErrorWhenProfileIs3() {
        Person person = new Person();
        person.setProfile(PersonProfilesEnum.COMPANY_CLIENT.get());


        service.verifyPreconditionToSave(person);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnErrorWhenProfileIs4() {
        Person person = new Person();
        person.setProfile(PersonProfilesEnum.INDIVIDUAL_CLIENT.get());


        service.verifyPreconditionToSave(person);

    }
}