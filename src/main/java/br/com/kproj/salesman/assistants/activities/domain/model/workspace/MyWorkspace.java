package br.com.kproj.salesman.assistants.activities.domain.model.workspace;


import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;

public class MyWorkspace {

    private final Owner owner;


    public MyWorkspace(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }
}
