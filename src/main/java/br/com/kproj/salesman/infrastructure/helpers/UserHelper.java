package br.com.kproj.salesman.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {


	@Autowired
	private UserEntityRepository repository;

	public Iterable<UserEntity> findAll() {
		return repository.findAll();
	}


}
