package br.com.kproj.salesman.accounts.customers.domain.model.contact;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Contact extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
