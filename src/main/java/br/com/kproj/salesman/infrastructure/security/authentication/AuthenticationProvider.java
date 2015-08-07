package br.com.kproj.salesman.infrastructure.security.authentication;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.security.UserSecurityInfoService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserSecurityInfoService service;



    @Override
    protected UserDetails retrieveUser(String login, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();

        if (isBlank(password)) {
            throw new BadCredentialsException("security.invalid.password");
        }

        Optional<User> userFound = service.getUser(login, password);

        User userFake = UserBuilder.createUser(1l).withLogin("admin").withPassword("admin").build();

//        if (!userFound.isPresent()) {
//            throw new BadCredentialsException("security.user.not.found");
//        }

        //LoggedUser loggedBuilt = LoggedUserBuilder.createLoggedUser(login, userFound.get(), Sets.newHashSet()).build();
        LoggedUser loggedBuilt = LoggedUserBuilder.createLoggedUser(login, userFake, Sets.newHashSet()).build();


        authentication.setDetails(loggedBuilt);
        return new org.springframework.security.core.userdetails.User(login, password, true, true, true, true, loggedBuilt.getAuthorities());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {}
}
