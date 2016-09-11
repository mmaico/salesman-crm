package br.com.kproj.salesman.delivery.application.tasks;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.task.ChecklistRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistApplicationImpl extends BaseModelServiceLegacyImpl<ChecklistEntity> implements ChecklistApplication {

    @Autowired
    private ChecklistRepository repository;


    @Override
    public ChecklistEntity register(ChecklistEntity checklistEntity) {

        return super.save(checklistEntity);
    }

    @Override
    public List<ChecklistEntity> findCheckListBy(TaskEntity taskEntity) {

        if (taskEntity == null || taskEntity.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findCheckListBy(taskEntity);
    }

    @Override
    public void completed(ChecklistEntity checklistEntity) {

        if (checklistEntity.isNew()) return;

        Optional<ChecklistEntity> result = repository.getOne(checklistEntity.getId());

        if(result.isPresent()) {
            result.get().setIsDone(Boolean.TRUE);
        }

    }

    public BaseRepositoryLegacy<ChecklistEntity, Long> getRepository() {
        return repository;
    }



}
