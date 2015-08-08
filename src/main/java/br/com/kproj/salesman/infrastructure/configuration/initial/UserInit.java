package br.com.kproj.salesman.infrastructure.configuration.initial;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;

@Component
public class UserInit implements InitialProcess {

	private static final String LOGIN = "admin";
	private static final String PASSWD = "admin";
	private static final String NAME = "Administrador do sistema";
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	@PostConstruct
	public void run() {
		
		long count = userRepository.count();
		if (count < 1) {
			byte[] byteArray = getAvatar();
			
			UserBuilder.createUser(1l)
				.withLogin(LOGIN).withPassword(PASSWD)
				.withName(NAME)
				.withAvatar(byteArray);
				
		}
	}

	private byte[] getAvatar() {
		InputStream result = UserInit.class.getResourceAsStream("/admin-icon.png");
		byte[] byteArray = null;
		try {
			byteArray = IOUtils.toByteArray(result);
		} catch (IOException e) {}
		return byteArray;
	}

	
}
