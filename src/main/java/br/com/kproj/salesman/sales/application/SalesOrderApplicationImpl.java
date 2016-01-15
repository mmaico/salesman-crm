package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.SalesOrderRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.sales.infrastructure.batch.generatebyproposal.convert.ProposalToSaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderApplicationImpl extends BaseModelServiceImpl<SalesOrder> implements SalesOrderApplication {


    @Autowired
    private SalesOrderRepository repository;

    @Autowired
    private ProposalToSaleOrder converter;


    @Override
    public SalesOrder register(BusinessProposal businessProposal) {

        SalesOrder salesOrder = converter.convert(businessProposal);
        return super.save(salesOrder);
    }


    @Override
    public BaseRepository<SalesOrder, Long> getRepository() {
        return repository;
    }


}
