package br.com.kproj.salesman.negotiation.proposal.approval.predicates;


import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.events.messages.ProposalAuditingAfterUpdateMessage;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ApprovalHadChangesPredicate implements Predicate<ProposalAuditingAfterUpdateMessage> {


    @Override
    public boolean test(ProposalAuditingAfterUpdateMessage auditing) {
        BusinessProposalAudinting before = auditing.getBefore();
        BusinessProposalAudinting after = auditing.getAfter();


        String saleablesItemsStored = JsonPath.read(before.getInfo(), "$.saleableItems").toString();
        String paymentItemsStored = JsonPath.read(before.getInfo(), "$.paymentItems").toString();

        String saleablesItemsNew = JsonPath.read(after.getInfo(), "$.saleableItems").toString();
        String paymentItemsNew = JsonPath.read(after.getInfo(), "$.paymentItems").toString();

        String regionNew = JsonPath.read(after.getInfo(), "$.operationRegion").toString();
        String regionStored = JsonPath.read(before.getInfo(), "$.operationRegion").toString();

        String clientNew = JsonPath.read(after.getInfo(), "$.client").toString();
        String clientStored = JsonPath.read(before.getInfo(), "$.client").toString();


        return !(saleablesItemsStored.equals(saleablesItemsNew)
                && paymentItemsStored.equals(paymentItemsNew)
                && regionStored.equals(regionNew)
                && clientStored.equals(clientNew));


    }
}
