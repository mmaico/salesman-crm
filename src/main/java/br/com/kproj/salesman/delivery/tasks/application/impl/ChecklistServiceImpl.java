package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.ChecklistFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistForTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service("checklistFacadeDeliveryModule")
public class ChecklistServiceImpl implements ChecklistFacade {

    @Autowired
    private ChecklistRepository repository;


    @Override
    public Collection<Checklist> findAll(Task task) {
        return repository.findAll(task);
    }

    @Override
    public void completed(Checklist checklist) {

        checklist.marksAsCompleted();
    }

//    @Override
//    public void addChecklist(ChecklistForTask checklistForTask) {
//        checklistValidator.checkRules(checklistForTask);
//        Optional<Task> task = repository.findOne(checklistForTask.getTaskId());
//
//        task.get().addCheckList(checklistForTask.getChecklist());
//    }
}
