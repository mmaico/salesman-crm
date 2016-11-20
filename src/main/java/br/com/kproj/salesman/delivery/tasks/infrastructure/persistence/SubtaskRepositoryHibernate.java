package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.SubtaskRepositorySpringData;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.TaskRepositorySpringData;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.translate.TaskEntityToTaskConverter;
import br.com.kproj.salesman.infrastructure.entity.task.RootTaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.SubtaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTypeEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import com.trex.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;


@Repository
public class SubtaskRepositoryHibernate extends BaseRespositoryImpl<Subtask, SubtaskEntity> implements SubtaskRepository {


    private SubtaskRepositorySpringData repository;
    private TaskRepositorySpringData taskRepository;

    private TaskEntityToTaskConverter converter;

    @Autowired
    public SubtaskRepositoryHibernate(SubtaskRepositorySpringData repository, TaskEntityToTaskConverter converter,
                                      TaskRepositorySpringData taskRepository) {
        this.repository = repository;
        this.converter = converter;
        this.taskRepository = taskRepository;
    }


    @Override
    public Iterable<Subtask> findAll(RootTask rootTask, Pageable pageable) {
        Page<SubtaskEntity> entities = repository.findAllByRoot(rootTask.getId(), pageable);

        List<Subtask> subtasks = entities.getContent()
                .stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());

        return new PageImpl<>(subtasks, pageable, entities.getTotalElements());
    }

    @Override
    public void delete(Subtask task) {

    }

    @Override
    public Optional<Subtask> add(Subtask subtask, RootTask task) {
        TaskEntity taskEntity = taskRepository.findOne(subtask.getId());

        SubtaskEntity subtaskEntity = new SubtaskEntity(subtask.getId());
        subtaskEntity.setTask(taskEntity);
        subtaskEntity.setParent(new RootTaskEntity(task.getId()));
        taskEntity.setType(TaskTypeEntity.SUBTASK);

        return Optional.of(getConverter().convert(repository.save(subtaskEntity)));
    }

    public Optional<Subtask> save(Subtask entity) {

        return Optional.empty();
    }


    @Override
    public BaseRepositoryLegacy<SubtaskEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SubtaskEntity, Subtask> getConverter() {
        return (subtaskEntity, args) -> {
            Subtask subtask = new Subtask(subtaskEntity.getId());
            Task task = converter.convert(subtaskEntity.getTask());
            from(task).merge(subtask);

            subtask.setParent(new RootTask(subtaskEntity.getParent().getId()));

            return subtask;
        };
    }

}
