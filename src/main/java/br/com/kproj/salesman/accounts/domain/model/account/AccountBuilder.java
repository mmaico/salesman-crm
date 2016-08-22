package br.com.kproj.salesman.accounts.domain.model.account;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class AccountBuilder extends AbstractBuilder<Account>  {

	public AccountBuilder() {
		this.entity = new Account();
	}

	public AccountBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public AccountBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}
	
	public AccountBuilder withSite(String site) {
		this.entity.setSite(site);
		return this;
	}
	
	public AccountBuilder withDescription(String description) {
		this.entity.setDescription(description);
		return this;
	}
	
	public AccountBuilder withPhone(String phone) {
		this.entity.setPhone(phone);
		return this;
	}

	public AccountBuilder withEmployers(Integer quantity) {
		this.entity.setEmployers(quantity);
		return this;
	}
	
	public static AccountBuilder createAccount(Long id) {
		return new AccountBuilder(id);
	}

	public static AccountBuilder createAccount() {
		return new AccountBuilder();
	}
}
