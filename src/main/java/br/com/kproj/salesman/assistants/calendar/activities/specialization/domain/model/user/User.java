package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.user;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class User extends ModelIdentifiable {

    private Long id;

    public User(Long id) {
        this();
        this.id = id;
    }

    public User() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static User user() {
        return new User();
    }
}
