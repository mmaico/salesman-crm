package br.com.kproj.salesman.infrastructure.repository.task;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class TaskTemplateRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private TaskTemplateRepository repository;

    @Test
    public void shouldReturnAllTemplateAssociatedToTask() {
        SaleableUnitEntity saleable = SaleableUnitBuilder.createSaleableUnit(1l).build();

        List<TaskTemplateEntity> result = repository.findTaskTemplateBy(saleable);

        assertThat(result.size(), is(2));
    }

    @Test
    public void shouldReturnTrueWhenTaksIsSonOfOtherTaksTemplate() {
        TaskTemplateEntity taskTemplateEntity = new TaskTemplateEntity();
        taskTemplateEntity.setId(3l);

        Boolean result = repository.isSomeonesSon(taskTemplateEntity);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenTaskIsNotSonOfOtherTaskTempalte() {
        TaskTemplateEntity taskTemplateEntity = new TaskTemplateEntity();
        taskTemplateEntity.setId(1l);

        Boolean result = repository.isSomeonesSon(taskTemplateEntity);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    @Transactional
    public void shouldLoadChecklistOfTaskTemplate() {
        TaskTemplateEntity taskTemplateEntity = new TaskTemplateEntity();
        taskTemplateEntity.setId(1l);

        TaskTemplateEntity result = repository.findOne(1l);

        assertThat(result.getChecklistTemplateEntities().size(), is(2));
    }

    @Test
    public void shouldReturnAllTemplatesOnlyRoot() {
        SaleableUnitEntity saleable = SaleableUnitBuilder.createSaleableUnit(1l).build();

        List<TaskTemplateEntity> result = repository.findTaskTemplateRootBy(saleable);

        assertThat(result.size(), is(2));
        assertThat(result.get(0).getId(), is(1l));
        assertThat(result.get(1).getId(), is(2l));
    }

    @Test
    public void shouldReturnTheTemplateTaskParent() {
        TaskTemplateEntity taskTemplateEntity = new TaskTemplateEntity();
        taskTemplateEntity.setId(3l);

        Optional<TaskTemplateEntity> result = repository.findParent(taskTemplateEntity);

        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get().getId(), is(1l));
    }


}