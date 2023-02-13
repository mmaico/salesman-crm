package br.com.kproj.salesman.notifications.delivery.domain.model.user;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Receiver extends ModelIdentifiable {

    private Long id;

    public Receiver(Long id) {
        this.id = id;
    }

    public Receiver() {}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
