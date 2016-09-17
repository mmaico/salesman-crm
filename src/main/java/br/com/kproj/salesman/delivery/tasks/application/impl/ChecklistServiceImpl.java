package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.ChecklistFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChecklistServiceImpl implements ChecklistFacade {

    @Autowired
    private ChecklistRepository repository;


    @Override
    public Collection<Checklist> findAll(Task task) {
        return repository.findAll(task);
    }

    @Override
    public void completed(Checklist checklist) {

        checklist.makeCompleted();
    }
}
