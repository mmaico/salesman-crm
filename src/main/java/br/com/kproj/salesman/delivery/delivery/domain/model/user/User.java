package br.com.kproj.salesman.delivery.delivery.domain.model.user;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class User extends ModelIdentifiable {

    private Long id;

    public User(){}
    public User(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
