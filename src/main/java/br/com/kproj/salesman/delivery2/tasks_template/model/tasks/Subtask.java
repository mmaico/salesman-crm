package br.com.kproj.salesman.delivery2.tasks_template.model.tasks;

import com.trex.shared.annotations.Model;

@Model
public class Subtask extends Task {

    private Task parent;

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }
}
