package br.com.kproj.salesman.infrastructure.entity.enums;

import br.com.kproj.salesman.infrastructure.entity.person.Company;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


public class EntityNameTest {

    @Test
    public void shouldReturnEnumWhenFound() {

        EntityName entityName = EntityName.get(BusinessProposalEntity.class);
        EntityName entityName1 = EntityName.get(Person.class);

        MatcherAssert.assertThat(entityName, is(EntityName.BUSINESS_PROPOSAL));
        MatcherAssert.assertThat(entityName1, is(EntityName.PERSON));
    }

    @Test
    public void shouldReturnEnumUsingSubClass() {

        EntityName entityName1 = EntityName.get(Company.class);

        MatcherAssert.assertThat(entityName1, is(EntityName.PERSON));
    }
}