package br.com.kproj.salesman.incidents.domain.model.user;

import br.com.kproj.salesman.incidents.domain.model.incident.Incident;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentRepository;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private IncidentRepository repository;

    public User() {
        autowire(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Incident> save(Incident incident) {
        incident.setCreatedBy(this);
        return repository.save(incident);
    }
}
