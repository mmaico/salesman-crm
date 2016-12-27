package br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import com.google.common.collect.Lists;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Model
public class RootActivity extends Activity {

    private List<SubActivity> children = Lists.newArrayList();

    @Autowired
    private RootActivityRepository repository;

    public RootActivity() {
        AutowireHelper.autowire(this);
    }

    public RootActivity(Long id) {
        this();
        super.setId(id);
    }

    public List<SubActivity> getChildren() {
        return children;
    }

    public void setChildren(List<SubActivity> children) {
        this.children = children;
    }

    public Optional<RootActivity> makeRoot() {
        return repository.register(this);
    }
}
