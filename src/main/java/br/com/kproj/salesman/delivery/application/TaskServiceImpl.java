package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.domain.TaskDomainService;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.BeanUtils;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.ModelHelper.isNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@Service
public class TaskServiceImpl extends BaseModelServiceImpl<Task> implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskDomainService service;



    @Override
    public Task register(Task task) {

        if (!task.isNew()) {
            Task taskLoaded = repository.findOne(task.getId());
            hasErrors(isNull(taskLoaded) ? newHashSet("task.notfound") : emptySet())
                    .throwing(ValidationException.class);

            BeanUtils.create().copyProperties(taskLoaded, task);
            service.checkBusinessRulesFor(taskLoaded);

            return repository.save(taskLoaded);
        } else {
            service.checkBusinessRulesFor(task);
            service.prepareToSave(task);

            return repository.save(task);
        }

    }

    @Override
    public List<Task> findBySaleOrder(SalesOrder salesOrder) {

        hasErrors(isNull(salesOrder) || salesOrder.isNew() ? newHashSet("invalid.salesorder.list.tasks") : emptySet())
                .throwing(ValidationException.class);

        return repository.findBySalesOrder(salesOrder);

    }

    @Override
    public Boolean isSomeonesSon(Task task) {

        if (task == null || task.isNew()) {
            return Boolean.FALSE;
        }

        return repository.isSomeonesSon(task);
    }

    @Override
    public BaseRepository<Task, Long> getRepository() {
        return repository;
    }


}
