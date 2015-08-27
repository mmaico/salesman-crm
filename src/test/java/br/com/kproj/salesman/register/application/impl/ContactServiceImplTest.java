package br.com.kproj.salesman.register.application.impl;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.register.application.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

	@InjectMocks
	private ContactServiceImpl service;
	
	@Mock
    private ContactRepository contactRepository;

	@Mock
    private ClientService clientService;
	
	@Test
	public void shouldSaveANewContact() {
		Person personMocked = Mockito.mock(Person.class);
		Person personLoadedMocked = Mockito.mock(Person.class);
		Contact contact = new Contact();
		
		given(personMocked.getId()).willReturn(2l);
		given(clientService.getOne(2l)).willReturn(of(personLoadedMocked));
		
		service.register(personMocked, contact);
		
		verify(personLoadedMocked).addContact(contact);
		verify(clientService).save(personLoadedMocked);
		
	}
	
	@Test(expected = ValidationException.class)
	public void shouldThrowExceptionWhenPersonNotExist() {
		Person personMocked = Mockito.mock(Person.class);
		Person personLoadedMocked = null;
		Contact contact = new Contact();
		
		given(personMocked.getId()).willReturn(2l);
		given(clientService.getOne(2l)).willReturn(ofNullable(personLoadedMocked));
		
		service.register(personMocked, contact);
		
		verify(personLoadedMocked).addContact(contact);
	}
	
	@Test
	public void shouldUpdateContact() {
		Person personMocked = Mockito.mock(Person.class);
		Contact contact = new Contact();
		contact.setId(1l);
		Contact contactLoaded = Mockito.mock(Contact.class);
		
		given(contactRepository.findOne(1l)).willReturn(contactLoaded);
		
		service.register(personMocked, contact);
		
		verify(contactRepository).save(contactLoaded);
	}

}
