package br.com.kproj.salesman.infrastructure.security.authentication;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
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

    private UserSecurityInfoService service;

    @Autowired
    public AuthenticationProvider(UserSecurityInfoService service) {
        this.service = service;
    }

    @Override
    protected UserDetails retrieveUser(String login, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();

        if (isBlank(password)) {
            throw new BadCredentialsException("security.invalid.password");
        }

        Optional<UserEntity> userFound = service.getUser(login, password);

        if (!userFound.isPresent()) {
            throw new BadCredentialsException("security.user.not.found");
        }

        LoggedUser loggedUser = LoggedUserBuilder.createLoggedUser(login, Sets.newHashSet()).build();
        loggedUser.setName(userFound.get().getName());
        loggedUser.setId(userFound.get().getId());

        authentication.setDetails(loggedUser);
        return new org.springframework.security.core.userdetails.User(login, password, true, true, true, true, loggedUser.getAuthorities());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {}
}
