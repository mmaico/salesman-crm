package br.com.kproj.salesman.assistants.activities2.domain.model.personal;


import br.com.kproj.salesman.assistants.activities2.domain.model.user.Owner;

public interface ChangeStatusValidator {

    void checkRules(Owner owner, ChangeStatus changeStatus);
}
