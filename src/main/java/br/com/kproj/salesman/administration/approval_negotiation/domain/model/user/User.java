package br.com.kproj.salesman.administration.approval_negotiation.domain.model.user;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ApproverRepository repository;

    public User() {
        autowire(this);
    }

    public User(Long id) {
        this();
        this.id = id;
    }

    public Approver makeAvailable(Approver approver) {
        return repository.makeAvailable(approver);
    }

    public Approver makeUnavailable(Approver approver) {
        return repository.makeUnavailable(approver);
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
