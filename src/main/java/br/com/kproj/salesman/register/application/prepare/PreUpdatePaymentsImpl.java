package br.com.kproj.salesman.register.application.prepare;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.repository.ProposalPaymentRepository;
import br.com.kproj.salesman.negotiation.proposal.application.NegotiationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("paymentPreUpdate")
public class PreUpdatePaymentsImpl implements PreUpdateItems {


    @Autowired
    private NegotiationApplication application;

    @Autowired
    private BusinessProposalRepository repository;

    @Autowired
    private ProposalPaymentRepository paymentRepository;



    @Override
    public void preUpdate(BusinessProposal proposalNew) {

        if (proposalNew.isNew()) return;

        BusinessProposal proposalLoaded = application.getOne(proposalNew.getId()).get();

        if (proposalNew.getTotal().equals(proposalLoaded.getTotalToPay())) return;

        proposalLoaded.getPaymentItems().forEach(paymentItem -> paymentRepository.delete(paymentItem));
        proposalLoaded.getPaymentItems().clear();
        proposalNew.getPaymentItems().forEach(itemPayment -> proposalLoaded.addNewProposalPaymentItem(itemPayment));

        repository.save(proposalLoaded);
    }


}
