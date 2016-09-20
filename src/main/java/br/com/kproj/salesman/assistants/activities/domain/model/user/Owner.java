package br.com.kproj.salesman.assistants.activities.domain.model.user;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Status;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Model
public class Owner extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ActivityRepository repository;

    public Owner() {
        AutowireHelper.autowire(this);
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Owner changeStatus(Activity activity) {
        this.context.add(Activity.class, activity);
        return this;
    }

    public void toNewStatus(Status newStatus) {
        Activity activity = (Activity) this.context.get(Activity.class);
        repository.changeStatus(activity, newStatus);

    }
}
