package br.com.kproj.salesman.administration.users.domain.model.administrator;


import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.Optional;

@Model
public class Administrator extends ModelIdentifiable {

    private UserRepository repository;

    public Administrator() {
        AutowireHelper.autowire(this);
    }

    @Override
    public Long getId() {
        return null;
    }

    public Optional<User> register(User user) {
        Optional<User> userSaved = repository.save(user);
        return userSaved;
    }

    public User update(User user) {
        User userSaved = repository.update(user);
        return userSaved;
    }


    public static Administrator administrator() {
        return new Administrator();
    }
}
