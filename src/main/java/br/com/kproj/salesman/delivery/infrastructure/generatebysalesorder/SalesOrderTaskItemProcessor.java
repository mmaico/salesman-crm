package br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder;

import br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.convert.TaskTemplateToTask;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.task.TaskTemplateRepository;
import com.google.common.collect.Lists;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalesOrderTaskItemProcessor implements ItemProcessor<SalesOrderEntity, List<TaskEntity>> {

    @Autowired
    private TaskTemplateRepository repository;

    @Override
    public List<TaskEntity> process(SalesOrderEntity salesOrderEntity) throws Exception {

//        List<List<TaskEntity>> collect = salesOrderEntity.getSalesOrderItems()
//                .stream()
//                .map(item -> repository.findTaskTemplateBy(item.getSaleableAvailable(), salesOrderEntity.getOperationRegionEntity())
//                                .stream().map(template -> getConverter(salesOrderEntity).convert(template))
//                                .collect(Collectors.toList())
//                ).collect(Collectors.toList());
//
//        List<TaskEntity> allTaskEntities = Lists.newArrayList();
//        collect.stream().forEach(taskList -> allTaskEntities.addAll(taskList));

        return null;
    }

    protected TaskTemplateToTask getConverter(SalesOrderEntity salesOrderEntity) {
        return TaskTemplateToTask.create(salesOrderEntity);
    }
}
