package br.com.kproj.salesman.delivery.infrastructure.batch.generatebysalesorder;

import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.batch.item.database.HibernatePagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.persistence.EntityManager;
import java.util.List;


@Configuration
@EnableBatchProcessing
public class GenerateSalesOrderTaskBatchConfig {


    @Bean
    public ItemReader<SalesOrder> itemReader(EntityManager em) {

        HibernatePagingItemReader reader = new HibernatePagingItemReader();
        reader.setSessionFactory(em.getEntityManagerFactory().unwrap(SessionFactory.class));
        reader.setPageSize(100);
        reader.setQueryString("SELECT so FROM SalesOrder so WHERE so.taskGenerated = false");

        return reader;
    }

    @Bean
    public ItemWriter<List<Task>> itemWriter(EntityManager em) {
        HibernateItemWriter<List<Task>> writer = new HibernateItemWriter();
        writer.setSessionFactory(em.getEntityManagerFactory().unwrap(SessionFactory.class));

        return writer;
    }

    @Bean
    @DependsOn({"generateTasksStep"})
    public Job generateTaskJob(JobBuilderFactory factory, Step generateTasksStep) {
        return factory.get("generateTaskJob")
                .flow(generateTasksStep).end().build();
    }

    @Bean
    @DependsOn({"itemReader", "itemWriter"})
    public Step generateTasksStep(StepBuilderFactory factory,
                                                  ItemReader<SalesOrder> reader, ItemWriter<List<Task>> writer,
                                                  ItemProcessor<SalesOrder, List<Task>> processor) {
        return factory.get("generateTasksStep")
                    .<SalesOrder, List<Task>>chunk(100)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer).build();
    }


}
