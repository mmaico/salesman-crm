package br.com.kproj.salesman.auditing.delivery.domain.model.user;


import br.com.kproj.salesman.auditing.delivery.domain.model.audit.Auditing;
import br.com.kproj.salesman.auditing.delivery.domain.model.audit.AuditingRepository;
import br.com.kproj.salesman.auditing.delivery.domain.model.task.Task;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.auditing.delivery.domain.model.audit.AuditingBuilder.createNewAudit;


@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private AuditingRepository repository;

    public User() {
        AutowireHelper.autowire(this);
    }
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

    public User changed(Task task) {
        this.context.add(Task.class, task);
        return this;
    }

    public Optional<Auditing> andTheNewVersionIs(String data) {
        Task task = (Task) this.context.get(Task.class);
        Auditing auditing = createNewAudit().currentDate()
                .withData(data)
                .withTask(task)
                .withUser(this).build();

        return repository.save(auditing);
    }
}
