package br.com.kproj.salesman.assistants.activities.domain.model.checklist;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Checklist extends ModelIdentifiable {

    private Long id;

    private String name;

    private Boolean done;

    private Activity activity;

    public Checklist() {
        AutowireHelper.autowire(this);
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
