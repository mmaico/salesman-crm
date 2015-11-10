package br.com.kproj.salesman.infrastructure.repository.task;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

        assertThat(result.size(), is(2));
    }


}