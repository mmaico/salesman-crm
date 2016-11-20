package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import com.trex.shared.annotations.Model;

@Model
public class Subtask extends Task {

    private Task parent;

    public Subtask() {}

    public Subtask(Long id) {
        this.setId(id);
    }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }
}
