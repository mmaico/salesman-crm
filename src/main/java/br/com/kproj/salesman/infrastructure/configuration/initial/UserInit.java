package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Cria um usuario admin caso nao tenha nenhum usuario no banco.
 */
@Component
public class UserInit implements InitialProcess {

	private static final String LOGIN = "admin";
	private static final String PASSWD = "admin";
	private static final String NAME = "Administrador do sistema";
	
	@Autowired
	private UserEntityRepository userEntityRepository;


	@Override
	@PostConstruct
	public void run() {

		long count = userEntityRepository.count();
		if (count < 1) {
            UserEntity adminUser = UserEntityBuilder.createUser(1l)
                    .withLogin(LOGIN).withPassword(PASSWD)
                    .withName(NAME)
                    .build();

            userEntityRepository.save(adminUser);
        }
	}

	
}
