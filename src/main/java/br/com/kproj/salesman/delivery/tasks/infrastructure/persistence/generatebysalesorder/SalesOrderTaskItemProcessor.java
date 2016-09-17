package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.generatebysalesorder;

import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.generatebysalesorder.convert.TaskTemplateToTask;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.TaskTemplateRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import com.google.common.collect.Lists;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalesOrderTaskItemProcessor implements ItemProcessor<SalesOrder, List<TaskEntity>> {

    @Autowired
    private TaskTemplateRepositorySpringData repository;

    @Override
    public List<TaskEntity> process(SalesOrder salesOrder) throws Exception {

        List<List<TaskEntity>> collect = salesOrder.getItems()
                .stream()
                .map(item -> repository.findTaskTemplateBy(new SaleableUnitEntity(item.getId()), new OperationRegionEntity(salesOrder.getRegion().getId()))
                                .stream().map(template -> getConverter(salesOrder).convert(template))
                                .collect(Collectors.toList())
                ).collect(Collectors.toList());

        List<TaskEntity> allTaskEntities = Lists.newArrayList();
        collect.stream().forEach(taskList -> allTaskEntities.addAll(taskList));

        return allTaskEntities;
    }

    protected TaskTemplateToTask getConverter(SalesOrder salesOrder) {
        return TaskTemplateToTask.create(salesOrder);
    }
}
