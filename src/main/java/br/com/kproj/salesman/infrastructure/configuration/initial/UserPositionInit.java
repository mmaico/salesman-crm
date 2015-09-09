package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.configuration.parsers.UserPositionParser;
import br.com.kproj.salesman.infrastructure.entity.UserPosition;
import br.com.kproj.salesman.infrastructure.repository.UserPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Cria os cargos de usuarios.
 */
@Component
public class UserPositionInit implements InitialProcess {


	@Autowired
	private UserPositionRepository repository;


	@Override
	@PostConstruct
	public void run() {

		long count = repository.count();
		if (count < 1) {
            List<UserPosition> result = UserPositionParser.getPositions();

            repository.save(result);
        }
	}

	
}
