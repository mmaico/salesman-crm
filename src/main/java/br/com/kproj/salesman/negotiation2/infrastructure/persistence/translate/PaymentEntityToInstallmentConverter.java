package br.com.kproj.salesman.negotiation2.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalPaymentItem;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation2.domain.model.payment.InstallmentItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.negotiation2.domain.model.payment.InstallmentItemBuilder.createSaleableItem;

@Component
public class PaymentEntityToInstallmentConverter implements Converter<List<ProposalPaymentItem>, List<InstallmentItem>> {

    @Override
    public List<InstallmentItem> convert(List<ProposalPaymentItem> items, Object... args) {

        List<InstallmentItem> installments = items.stream().map(item -> createSaleableItem(item.getId())
                .withDueDate(item.getDueDate())
                .withObservation(item.getObservation()).withValue(item.getValue()).build())
                .collect(Collectors.toList());

        return installments;
    }
}
