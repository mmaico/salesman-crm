package br.com.kproj.salesman.register.domain.contract;

import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;

public interface ProviderDomainService {

	void verifyPreconditionToSave(Provider provider);
}
