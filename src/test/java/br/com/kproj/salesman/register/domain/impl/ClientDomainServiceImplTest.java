package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.PersonProfile;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.domain.AddressDomainService;
import br.com.kproj.salesman.register.domain.ClientDomainServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientDomainServiceImplTest {

    @InjectMocks
    private ClientDomainServiceImpl clientDomainService;
    
    @Mock
    private AddressDomainService service;


    @Test
    public void shouldNotReturnErrorWhenProfileIs1() {
        Person person = new Person();
        person.setProfile(new PersonProfile(1l));


        clientDomainService.verifyPreconditionToSave(person);

    }

    @Test
    public void shouldNotReturnErrorWhenProfileIs2() {
        Person person = new Person();
        person.setProfile(new PersonProfile(2l));


        clientDomainService.verifyPreconditionToSave(person);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnErrorWhenProfileIs3() {
        Person person = new Person();
        person.setProfile(new PersonProfile(3l));


        clientDomainService.verifyPreconditionToSave(person);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnErrorWhenProfileIs4() {
        Person person = new Person();
        person.setProfile(new PersonProfile(4l));


        clientDomainService.verifyPreconditionToSave(person);

    }
}