package br.com.kproj.salesman.assistants.activities.domain.services;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Status;

@FunctionalInterface
public interface ChangeStatusActivity {

    void toNewStatus(Status newStatus);
}
