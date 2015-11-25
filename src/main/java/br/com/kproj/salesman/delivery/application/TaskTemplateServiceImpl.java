package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.domain.TaskTemplateDomainService;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.BeanUtils;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskTemplateRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.ModelHelper.isNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@Service
public class TaskTemplateServiceImpl extends BaseModelServiceImpl<TaskTemplate> implements TaskTemplateService {

    @Autowired
    private TaskTemplateRepository repository;

    @Autowired
    private TaskTemplateDomainService domainService;


    @Override
    public TaskTemplate register(TaskTemplate taskTemplate) {

        if (!taskTemplate.isNew()) {
            TaskTemplate taskTemplateLoaded = repository.findOne(taskTemplate.getId());
            hasErrors(isNull(taskTemplateLoaded) ? newHashSet("tasktemplate.notfound") : emptySet())
                    .throwing(ValidationException.class);

            BeanUtils.create().copyProperties(taskTemplateLoaded, taskTemplate);
            domainService.checkBusinessRulesFor(taskTemplateLoaded);
            return repository.save(taskTemplateLoaded);
        } else {
            domainService.checkBusinessRulesFor(taskTemplate);
            return repository.save(taskTemplate);
        }

    }

    @Override
    public BaseRepository<TaskTemplate, Long> getRepository() {
        return repository;
    }


}
