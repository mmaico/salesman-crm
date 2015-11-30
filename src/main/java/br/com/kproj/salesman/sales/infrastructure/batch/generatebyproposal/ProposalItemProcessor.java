package br.com.kproj.salesman.sales.infrastructure.batch.generatebyproposal;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.sales.infrastructure.batch.generatebyproposal.convert.ProposalToSaleOrder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalItemProcessor implements ItemProcessor<BusinessProposal, SalesOrder> {

    @Autowired
    private ProposalToSaleOrder converter;

    @Override
    public SalesOrder process(BusinessProposal item) throws Exception {
        return converter.convert(item);
    }
}
