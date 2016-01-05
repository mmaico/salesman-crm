package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BusinessProposalRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private BusinessProposalRepository repository;

    @Autowired
    private Gson gson;

    @Test
    public void should() {
        BusinessProposal proposal = repository.findOne(1l);

        String result = gson.toJson(proposal);

        System.out.println(result);
    }

}