package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ApproverProfileRepositoryIT extends AbstractIntegrationTest {


    @Autowired
    private ApproverProfileRepository repository;

    @Test
    public void shouldReturnTrueWheHasApproversAvailable() {

        Boolean result = repository.hasApprovers();

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnApproverProfileByUser() {
        UserEntity user = UserEntityBuilder.createUser(2l).build();
        Optional<ApproverEntity> result = repository.findByApprover(user);

        assertThat(result.isPresent(), is(Boolean.TRUE));
    }

}