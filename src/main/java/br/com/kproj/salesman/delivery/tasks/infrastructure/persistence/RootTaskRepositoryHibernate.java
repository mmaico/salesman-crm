package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.RootTaskRepositorySpringData;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.TaskRepositorySpringData;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.translate.TaskEntityToTaskConverter;
import br.com.kproj.salesman.infrastructure.entity.task.RootTaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTypeEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.mmaico.clone.BusinessModelClone.from;


@Repository
public class RootTaskRepositoryHibernate extends BaseRespositoryImpl<RootTask, RootTaskEntity> implements RootTaskRepository {

    private RootTaskRepositorySpringData repository;

    private TaskRepositorySpringData taskRepository;

    private TaskEntityToTaskConverter converter;

    @Autowired
    public RootTaskRepositoryHibernate(RootTaskRepositorySpringData repository, TaskRepositorySpringData taskRepository, TaskEntityToTaskConverter converter) {
        this.repository = repository;
        this.taskRepository = taskRepository;
        this.converter = converter;
    }


    @Override
    public void delete(RootTask task) {

    }

    public Optional<RootTask> save(RootTask entity) {
        TaskEntity taskEntity = taskRepository.findOne(entity.getId());

        RootTaskEntity rootTaskEntity = new RootTaskEntity();
        rootTaskEntity.setId(entity.getId());
        rootTaskEntity.setTask(taskEntity);
        taskEntity.setType(TaskTypeEntity.ROOTTASK);

        RootTaskEntity rootTaskSaved = repository.save(rootTaskEntity);
        return Optional.of(getConverter().convert(rootTaskSaved));
    }

    @Override
    public Iterable<RootTask> findAll(Long deliveryId, Pageable pageable) {
        Page<RootTaskEntity> rootTaskEntities = repository.findAllByDelivery(deliveryId, pageable);

        List<RootTask> rootTasks = rootTaskEntities.getContent().stream()
                .map(rootTaskEntity -> getConverter().convert(rootTaskEntity))
                .collect(Collectors.toList());

        return new PageImpl<>(rootTasks, pageable, rootTaskEntities.getTotalElements());
    }

    @Override
    public Converter<RootTaskEntity, RootTask> getConverter() {
        return (rootTaskEntity, args) -> {
            RootTask rootTask = new RootTask(rootTaskEntity.getId());
            Task task = converter.convert(rootTaskEntity.getTask());
            from(task).merge(rootTask);

            return rootTask;
        };
    }

    @Override
    public BaseRepositoryLegacy<RootTaskEntity, Long> getRepository() {
        return repository;
    }
}
