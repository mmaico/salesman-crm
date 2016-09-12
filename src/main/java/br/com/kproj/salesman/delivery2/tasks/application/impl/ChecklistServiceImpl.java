package br.com.kproj.salesman.delivery2.tasks.application.impl;

import br.com.kproj.salesman.delivery2.tasks.application.ChecklistFacade;
import br.com.kproj.salesman.delivery2.tasks.application.UserFacade;
import br.com.kproj.salesman.delivery2.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery2.tasks.domain.model.checklist.ChecklistForTask;
import br.com.kproj.salesman.delivery2.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery2.tasks.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.ChangeStatus;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.Subscribe;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.SubscribeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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
