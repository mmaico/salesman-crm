package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;

public class ProviderBuilder extends AbstractBuilder<Provider>  {

	public ProviderBuilder() {

	}

	public ProviderBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
    public ProviderBuilder withId(Long id) {
        this.entity.setId(id);
        return this;
    }
	public static ProviderBuilder createProvider(Long id) {
		return new ProviderBuilder(id);
	}

	public static ProviderBuilder createProvider() {
		return new ProviderBuilder();
	}
}
