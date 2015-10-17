package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

		Contact contact = new Contact();

		service.register(contact);

		verify(contactRepository).save(contact);
		
	}
	

	@Test
	public void shouldUpdateContact() {

		Contact contact = new Contact();
		contact.setId(1l);
		Contact contactLoaded = Mockito.mock(Contact.class);
		
		given(contactRepository.findOne(1l)).willReturn(contactLoaded);
		
		service.register(contact);
		
		verify(contactRepository).save(contactLoaded);
	}

}
