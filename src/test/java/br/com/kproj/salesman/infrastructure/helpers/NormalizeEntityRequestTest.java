package br.com.kproj.salesman.infrastructure.helpers;


import br.com.kproj.salesman.infrastructure.entity.Company;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class NormalizeEntityRequestTest {

	@InjectMocks
	private NormalizeEntityRequest normalize;
	
	@Test
	public void shouldDoNestedReferenceInTargetObject() throws Exception {
		Company company = getCompanyStub();
		
		normalize.doNestedReference(company);

		assertThat(company.getContacts().get(0).getClient(), sameInstance(company));
	}
	


	@Test
	public void shouldAddParamNameInEntities() throws Exception {
		this.normalize = spy(this.normalize);
		Company company = getCompanyStub();

		doReturn(parameters()).when(this.normalize).getRequest();

		normalize.addFieldsToUpdate(company);


		List<Contact> contacts = company.getContacts();

		assertThat(company.getFields().contains("name"), is(Boolean.TRUE));
		assertThat(company.getFields().contains("tradingName"), is(Boolean.TRUE));

		assertThat(contacts.get(0).getFields().contains("name"), is(Boolean.TRUE));
        assertThat(contacts.get(0).getFields().contains("email"), is(Boolean.TRUE));
	}


	@Test
	public void shouldHaveNestedListAttribute() throws Exception {
		this.normalize = spy(this.normalize);
		Company company = getCompanyStub();
		Set<String> parameters = Sets.newHashSet("contacts[0].name");

		doReturn(parameters).when(this.normalize).getRequest();

		normalize.addFieldsToUpdate(company);

		assertThat(company.getFields().contains("contacts"), is(Boolean.TRUE));
	}


	private Set<String> parameters() {
		Set<String> request = new HashSet<String>();
        request.add("id");
        request.add("name");
        request.add("tradingName");
        request.add("contacts[0].name");
        request.add("contacts[0].email");
        
        return request;
	}

	private Company getCompanyStub() {
		Company company = new Company();
		company.setName("teste");

		Contact contact = new Contact();
        contact.setEmail("email@email.com");
        contact.setName("tesste");

		company.setContacts(Arrays.asList(contact));
		
		return company;
	}


}
