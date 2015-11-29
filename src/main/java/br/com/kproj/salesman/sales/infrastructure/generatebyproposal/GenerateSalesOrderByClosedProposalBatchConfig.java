package br.com.kproj.salesman.sales.infrastructure.generatebyproposal;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
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

import javax.persistence.EntityManager;


@Configuration
@EnableBatchProcessing
public class GenerateSalesOrderByClosedProposalBatchConfig {


    @Bean
    public ItemReader<BusinessProposal> reader(EntityManager em) {

        HibernatePagingItemReader reader = new HibernatePagingItemReader();
        reader.setSessionFactory(em.getEntityManagerFactory().unwrap(SessionFactory.class));
        reader.setPageSize(100);
        reader.setQueryString("SELECT bp FROM BusinessProposal bp WHERE bp.temperature = 'CLOSED_WON'");

        return reader;
    }

    @Bean
    public ItemWriter<SalesOrder> writer(EntityManager em) {
        HibernateItemWriter<SalesOrder> writer = new HibernateItemWriter();
        writer.setSessionFactory(em.getEntityManagerFactory().unwrap(SessionFactory.class));

        return writer;
    }

    @Bean
    public Job importProposalJob(JobBuilderFactory factory, Step generateSalesOrderStep) {
        return factory.get("importProposalJob")
                .flow(generateSalesOrderStep).end().build();
    }

    @Bean
    public Step generateSalesOrderStep(StepBuilderFactory factory,
                                                  ItemReader<BusinessProposal> reader, ItemWriter<SalesOrder> writer,
                                                  ItemProcessor<BusinessProposal, SalesOrder> processor) {
        return factory.get("genetateSalesOrderByClosedProposalStep")
                    .<BusinessProposal, SalesOrder>chunk(100)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer).build();

    }


}
