package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.application.contract.ProviderApplication;
import br.com.kproj.salesman.register.domain.contract.AddressDomainService;
import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddressApplicationImplTest {

    @InjectMocks
    private AddressApplicationImpl service;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ClientApplication clientApplication;

    @Mock
    private AddressDomainService addressDomainService;

    @Mock
    private ProviderApplication providerApplication;

    @Test
    public void shouldSaveANewAddress() {
        Client clientMocked = mock(Client.class);
        Person personLoadedMocked = mock(Person.class);
        Address address = mock(Address.class);

        given(clientMocked.getId()).willReturn(2l);
        given(clientApplication.getOne(2l)).willReturn(Optional.of(personLoadedMocked));
        given(address.isNew()).willReturn(Boolean.TRUE);
        given(clientMocked.to()).willReturn(personLoadedMocked);

        service.register(address, clientMocked);

        verify(address).setPerson(personLoadedMocked);
        verify(addressDomainService).prepareToSave(address);
        verify(addressRepository).save(address);

    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenPersonNotExist() {
        Client personMocked = mock(Client.class);
        Person personLoadedMocked = null;
        Address address = new Address();


        given(personMocked.getId()).willReturn(2l);
        given(clientApplication.getOne(2l)).willReturn(Optional.ofNullable(personLoadedMocked));

        service.register(address, personMocked);

    }

    @Test
    public void shouldUpdateAddress() {
        Client clientMocked = mock(Client.class);
        Person personLoadedMocked = mock(Person.class);
        Address address = mock(Address.class);
        Address addressLoaded = mock(Address.class);

        given(clientMocked.getId()).willReturn(2l);
        given(clientApplication.getOne(2l)).willReturn(Optional.of(personLoadedMocked));
        given(address.isNew()).willReturn(Boolean.FALSE);
        given(address.getId()).willReturn(1l);
        given(addressRepository.findOne(1l)).willReturn(addressLoaded);
        given(clientMocked.to()).willReturn(personLoadedMocked);

        service.register(address, clientMocked);

        verify(address).setPerson(personLoadedMocked);
        verify(addressDomainService).prepareToSave(address);
        verify(addressRepository).save(addressLoaded);
    }

    @Test
    public void shouldSaveANewAddressProvider() {
        Provider providerMocked = mock(Provider.class);
        Person personLoadedMocked = mock(Person.class);
        Address address = mock(Address.class);

        given(providerMocked.getId()).willReturn(2l);
        given(providerApplication.getOne(2l)).willReturn(Optional.of(personLoadedMocked));
        given(address.isNew()).willReturn(Boolean.TRUE);
        given(providerMocked.to()).willReturn(personLoadedMocked);

        service.register(address, providerMocked);

        verify(address).setPerson(personLoadedMocked);
        verify(addressDomainService).prepareToSave(address);
        verify(addressRepository).save(address);

    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenPersonNotExistProvider() {
        Provider personMocked = mock(Provider.class);
        Person personLoadedMocked = null;
        Address address = new Address();


        given(personMocked.getId()).willReturn(2l);
        given(providerApplication.getOne(2l)).willReturn(Optional.ofNullable(personLoadedMocked));

        service.register(address, personMocked);

    }

    @Test
    public void shouldUpdateAddressProvider() {
        Provider providerMocked = mock(Provider.class);
        Person personLoadedMocked = mock(Person.class);
        Address address = mock(Address.class);
        Address addressLoaded = mock(Address.class);

        given(providerMocked.getId()).willReturn(2l);
        given(providerApplication.getOne(2l)).willReturn(Optional.of(personLoadedMocked));
        given(address.isNew()).willReturn(Boolean.FALSE);
        given(address.getId()).willReturn(1l);
        given(addressRepository.findOne(1l)).willReturn(addressLoaded);
        given(providerMocked.to()).willReturn(personLoadedMocked);

        service.register(address, providerMocked);

        verify(address).setPerson(personLoadedMocked);
        verify(addressDomainService).prepareToSave(address);
        verify(addressRepository).save(addressLoaded);
    }

    @Test
    public void shouldGetAddressByClient() {
        Client clientmock = mock(Client.class);
        List<Address> addresses = Lists.newArrayList(mock(Address.class));

        given(addressRepository.findByPerson(any(Person.class))).willReturn(addresses);

        List<Address> result = service.getAddressesByClient(clientmock);

        assertThat(result, sameInstance(addresses));
    }

    @Test
    public void shouldGetAddressByProvider() {
        Provider providerMock = mock(Provider.class);
        List<Address> addresses = Lists.newArrayList(mock(Address.class));

        given(addressRepository.findByPerson(any(Person.class))).willReturn(addresses);

        List<Address> result = service.getAddressesByProvider(providerMock);

        assertThat(result, sameInstance(addresses));
    }
}