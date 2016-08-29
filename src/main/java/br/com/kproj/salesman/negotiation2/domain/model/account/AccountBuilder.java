package br.com.kproj.salesman.negotiation2.domain.model.account;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class AccountBuilder extends AbstractBuilder<Account>  {

	public AccountBuilder() {
		this.entity = new Account();
	}

	public AccountBuilder(Long id) {
		this();
		this.entity.setId(id);
	}


	public static AccountBuilder createAccount(Long id) {
		return new AccountBuilder(id);
	}

	public static AccountBuilder createAccount() {
		return new AccountBuilder();
	}
}
