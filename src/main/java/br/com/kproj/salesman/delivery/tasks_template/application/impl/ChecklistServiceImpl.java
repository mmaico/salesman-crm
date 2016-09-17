package br.com.kproj.salesman.delivery.tasks_template.application.impl;


import br.com.kproj.salesman.delivery.tasks_template.application.ChecklistFacade;
import br.com.kproj.salesman.delivery.tasks_template.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks_template.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks_template.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("checklistTemplateService")
public class ChecklistServiceImpl extends BaseModelServiceImpl<Checklist> implements ChecklistFacade {

    @Autowired
    private ChecklistRepository repository;


    @Override
    public Collection<Checklist> findAll(Task task) {
        return repository.findAll(task);
    }

    @Override
    public void delete(Checklist checklist) {
        repository.delete(checklist);
    }


    @Override
    public BaseRepository<Checklist, Long> getRepository() {
        return repository;
    }


}
