package br.com.kproj.salesman.administration.users.infrastructure.persistence.translator;

import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.administration.users.domain.model.user.UserBuilder.createUser;


@Component
public class UserEntityToUserConverter implements Converter<UserEntity, User> {

    @Autowired
    private BranchEntityToBranchConverter branchConverter;

    @Autowired
    private PositionEntityToPositionConverter positionConverter;

    @Override
    public User convert(UserEntity userEntity, Object... args) {
        User user = createUser(userEntity.getId())
                .withEmail(userEntity.getEmail())
                .withName(userEntity.getName())
                .withLastname(userEntity.getLastname())
                .withLogin(userEntity.getLogin())
                .withPassword(userEntity.getPassword()).build();

        user.setBranch(branchConverter.convert(userEntity.getBranch()));

        user.setPosition(positionConverter.convert(userEntity.getPosition()));

        return user;
    }
}
