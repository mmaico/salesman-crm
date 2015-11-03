package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.domain.AddressDomainService;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository contactRepository;

    @Mock
    private ClientService clientService;
    
    @Mock
    private AddressDomainService service;

    @Test
    public void shouldSaveANewContact() {
        Person personMocked = Mockito.mock(Person.class);
        Person personLoadedMocked = Mockito.mock(Person.class);
        Address address = new Address();

        given(personMocked.getId()).willReturn(2l);
        given(clientService.getOne(2l)).willReturn(Optional.of(personLoadedMocked));

        addressService.register(personMocked, address);

        
        assertThat(address.getPerson(), sameInstance(personLoadedMocked));
        verify(service).prepareToSave(address);
        verify(contactRepository).save(address);

    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenPersonNotExist() {
        Person personMocked = Mockito.mock(Person.class);
        Person personLoadedMocked = null;
        Address address = new Address();

        given(personMocked.getId()).willReturn(2l);
        given(clientService.getOne(2l)).willReturn(Optional.ofNullable(personLoadedMocked));

        addressService.register(personMocked, address);

        verify(personLoadedMocked).addAddress(address);
    }

    @Test
    public void shouldUpdateContact() {
        Person personMocked = Mockito.mock(Person.class);
        Address address = new Address();
        address.setId(1l);
        Address addressLoaded = Mockito.mock(Address.class);

        given(contactRepository.findOne(1l)).willReturn(addressLoaded);

        addressService.register(personMocked, address);

        
        verify(service).prepareToSave(address);
        verify(contactRepository).save(addressLoaded);
    }
}