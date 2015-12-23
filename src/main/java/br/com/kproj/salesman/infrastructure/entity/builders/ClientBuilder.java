package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;

public class ClientBuilder extends AbstractBuilder<Client>  {

	public ClientBuilder() {
		this.entity = new Person();
	}

	public ClientBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
    public ClientBuilder withId(Long id) {
        this.entity.setId(id);
        return this;
    }
	public static ClientBuilder createClient(Long id) {
		return new ClientBuilder(id);
	}

	public static ClientBuilder createClient() {
		return new ClientBuilder();
	}
}
