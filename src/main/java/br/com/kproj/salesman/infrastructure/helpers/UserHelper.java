package br.com.kproj.salesman.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {


	@Autowired
	private UserRepository repository;

	public Iterable<User> findAll() {
		return repository.findAll();
	}


}
