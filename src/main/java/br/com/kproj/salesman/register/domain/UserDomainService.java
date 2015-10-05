package br.com.kproj.salesman.register.domain;


import br.com.kproj.salesman.infrastructure.entity.User;

public interface UserDomainService {



    void verifyPreconditions(User user);
}
