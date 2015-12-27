package br.com.kproj.salesman.infrastructure.repository.task;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class TaskTemplateRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private TaskTemplateRepository repository;

    @Test
    public void shouldReturnAllTemplateAssociatedToTask() {
        SaleableUnit saleable = SaleableUnitBuilder.createSaleableUnit(1l).build();

        List<TaskTemplate> result = repository.findTaskTemplateBy(saleable);

        assertThat(result.size(), is(4));
    }

    @Test
    public void shouldReturnTrueWhenTaksIsSonOfOtherTaksTemplate() {
        TaskTemplate taskTemplate = new TaskTemplate();
        taskTemplate.setId(3l);

        Boolean result = repository.isSomeonesSon(taskTemplate);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenTaskIsNotSonOfOtherTaskTempalte() {
        TaskTemplate taskTemplate = new TaskTemplate();
        taskTemplate.setId(1l);

        Boolean result = repository.isSomeonesSon(taskTemplate);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    @Transactional
    public void shouldLoadChecklistOfTaskTemplate() {
        TaskTemplate taskTemplate = new TaskTemplate();
        taskTemplate.setId(1l);

        TaskTemplate result = repository.findOne(1l);

        assertThat(result.getChecklistTemplates().size(), is(2));
    }

    @Test
    public void shouldReturnAllTemplatesOnlyRoot() {
        SaleableUnit saleable = SaleableUnitBuilder.createSaleableUnit(1l).build();

        List<TaskTemplate> result = repository.findTaskTemplateRootBy(saleable);

        assertThat(result.size(), is(2));
        assertThat(result.get(0).getId(), is(1l));
        assertThat(result.get(1).getId(), is(2l));
    }


}