package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.application.contract.ProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContactApplicationImplTest {

	@InjectMocks
	private ContactApplicationImpl service;
	
	@Mock
    private ContactRepository contactRepository;

	@Mock
    private ClientApplication clientApplication;

	@Mock
	private ProviderApplication providerApplication;
	
	@Test
	public void shouldSaveANewContact() {
		ContactEntity contact = new ContactEntity();

		service.register(contact);

		verify(contactRepository).save(contact);
	}
	

	@Test
	public void shouldUpdateContact() {
		ContactEntity contact = new ContactEntity();
		contact.setId(1l);
		ContactEntity contactLoaded = mock(ContactEntity.class);
		
		given(contactRepository.findOne(1l)).willReturn(contactLoaded);
		
		service.register(contact);
		
		verify(contactRepository).save(contactLoaded);
	}

	@Test
	public void shouldRegisterContactWithClient() {
		Client clientMock = mock(Client.class);
		Person clientMockDB = mock(Person.class);
		ContactEntity contactMock = mock(ContactEntity.class);
		ContactEntity contactMockDB = mock(ContactEntity.class);

		given(clientMock.getId()).willReturn(1l);
		given(contactMock.isNew()).willReturn(Boolean.TRUE);
		given(clientApplication.getOne(1l)).willReturn(Optional.of(clientMockDB));
		given(contactRepository.save(contactMock)).willReturn(contactMockDB);

		ContactEntity result = service.register(contactMock, clientMock);

		assertThat(result, sameInstance(contactMockDB));
		verify(contactMock).setPerson(any(Person.class));
	}

	@Test(expected = ValidationException.class)
	public void shouldThrowExceptionWhenClientNoId() {
		Client clientMock = mock(Client.class);
		ContactEntity contactMock = mock(ContactEntity.class);

		given(clientMock.getId()).willReturn(null);

		service.register(contactMock, clientMock);

	}

	@Test
	public void shouldRegisterContactWithProvider() {
		Provider providerMock = mock(Provider.class);
		Person providerMockDB = mock(Person.class);
		ContactEntity contactMock = mock(ContactEntity.class);
		ContactEntity contactMockDB = mock(ContactEntity.class);

		given(providerMock.getId()).willReturn(1l);
		given(contactMock.isNew()).willReturn(Boolean.TRUE);
		given(providerApplication.getOne(1l)).willReturn(Optional.of(providerMockDB));
		given(contactRepository.save(contactMock)).willReturn(contactMockDB);

		ContactEntity result = service.register(contactMock, providerMock);

		assertThat(result, sameInstance(contactMockDB));
		verify(contactMock).setPerson(any(Person.class));
	}

	@Test(expected = ValidationException.class)
	public void shouldThrowExceptionWhenProviderNoId() {
		Provider providerMock = mock(Provider.class);
		ContactEntity contactMock = mock(ContactEntity.class);

		given(providerMock.getId()).willReturn(null);

		service.register(contactMock, providerMock);

	}

}
