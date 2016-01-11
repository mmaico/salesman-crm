package br.com.kproj.salesman.negotiation.approval.predicates;

import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.events.messages.ProposalAuditingAfterUpdateMessage;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@RunWith(MockitoJUnitRunner.class)
public class ApprovalHadChangesPredicateTest {

    @InjectMocks
    private ApprovalHadChangesPredicate predicate;



    @Test
    public void shouldReturnFalseWhenNotHadChanges() throws IOException {
        String jsonNew = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposal01.json"));
        String jsonOld = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposal01.json"));
        BusinessProposalAudinting audintingBefore = new BusinessProposalAudinting();
        audintingBefore.setInfo(jsonNew);

        BusinessProposalAudinting audintingAfter = new BusinessProposalAudinting();
        audintingAfter.setInfo(jsonOld);

        ProposalAuditingAfterUpdateMessage message = ProposalAuditingAfterUpdateMessage.create(audintingBefore, audintingAfter, null);

        boolean result = predicate.test(message);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void shouldReturnFalseWhenChangeInfoNoVerified() throws IOException {
        String jsonNew = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposal01.json"));
        String jsonOld = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposalInfosNoVerified.json"));
        BusinessProposalAudinting audintingBefore = new BusinessProposalAudinting();
        audintingBefore.setInfo(jsonNew);

        BusinessProposalAudinting audintingAfter = new BusinessProposalAudinting();
        audintingAfter.setInfo(jsonOld);

        ProposalAuditingAfterUpdateMessage message = ProposalAuditingAfterUpdateMessage.create(audintingBefore, audintingAfter, null);

        boolean result = predicate.test(message);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueWhenHadChangesOnRegion() throws IOException {
        String jsonNew = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposal01.json"));
        String jsonOld = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposalChangeRegion.json"));
        BusinessProposalAudinting audintingBefore = new BusinessProposalAudinting();
        audintingBefore.setInfo(jsonNew);

        BusinessProposalAudinting audintingAfter = new BusinessProposalAudinting();
        audintingAfter.setInfo(jsonOld);

        ProposalAuditingAfterUpdateMessage message = ProposalAuditingAfterUpdateMessage.create(audintingBefore, audintingAfter, null);

        boolean result = predicate.test(message);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnTrueWhenHadChangesOnPayments() throws IOException {
        String jsonNew = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposal01.json"));
        String jsonOld = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposalChangeOnPayments.json"));
        BusinessProposalAudinting audintingBefore = new BusinessProposalAudinting();
        audintingBefore.setInfo(jsonNew);

        BusinessProposalAudinting audintingAfter = new BusinessProposalAudinting();
        audintingAfter.setInfo(jsonOld);

        ProposalAuditingAfterUpdateMessage message = ProposalAuditingAfterUpdateMessage.create(audintingBefore, audintingAfter, null);

        boolean result = predicate.test(message);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnTrueWhenHadChangesOnSaleablesItems() throws IOException {
        String jsonNew = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposal01.json"));
        String jsonOld = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposalChangeOnSaleablesItems.json"));
        BusinessProposalAudinting audintingBefore = new BusinessProposalAudinting();
        audintingBefore.setInfo(jsonNew);

        BusinessProposalAudinting audintingAfter = new BusinessProposalAudinting();
        audintingAfter.setInfo(jsonOld);

        ProposalAuditingAfterUpdateMessage message = ProposalAuditingAfterUpdateMessage.create(audintingBefore, audintingAfter, null);

        boolean result = predicate.test(message);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnTrueWhenHadChangesOnClient() throws IOException {
        String jsonNew = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposal01.json"));
        String jsonOld = IOUtils.toString(ApprovalHadChangesPredicateTest.class.getResourceAsStream("/jsons/proposalChangeOnClient.json"));
        BusinessProposalAudinting audintingBefore = new BusinessProposalAudinting();
        audintingBefore.setInfo(jsonNew);

        BusinessProposalAudinting audintingAfter = new BusinessProposalAudinting();
        audintingAfter.setInfo(jsonOld);

        ProposalAuditingAfterUpdateMessage message = ProposalAuditingAfterUpdateMessage.create(audintingBefore, audintingAfter, null);

        boolean result = predicate.test(message);

        assertThat(result, is(Boolean.TRUE));
    }

}