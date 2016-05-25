package br.com.kproj.salesman.negotiation.proposal.view.helpers;


import br.com.kproj.salesman.negotiation.proposal.view.dto.PaymentDTO;
import br.com.kproj.salesman.negotiation.proposal.view.dto.PaymentDefinitionDTO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class PaymentDetailHelper {

    @Autowired
    private ProposalSaleablesHelper proposalSaleablesHelper;

    public PaymentDTO generate(PaymentDefinitionDTO dto) {
        PaymentDTO payment = new PaymentDTO();
        BigDecimal totalSale = proposalSaleablesHelper.getTotalItems();

        for (int i = 0; i < dto.getInstallments(); i++) {

            if (isLastInstallment(dto, i)) {
                BigDecimal result = payment.getInstallments().stream().map(item -> item.getValue())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                Date dueDate = new DateTime(dto.getFirstDateDue()).plusDays(i * dto.getDateIntervalBetweenDates()).toDate();
                payment.addInstallment(dueDate, totalSale.subtract(result));
            } else {
                BigDecimal intallment = totalSale.divide(new BigDecimal(dto.getInstallments()), 2, BigDecimal.ROUND_DOWN);
                Date dueDate = new DateTime(dto.getFirstDateDue()).plusDays(i * dto.getDateIntervalBetweenDates()).toDate();
                payment.addInstallment(dueDate, intallment);
            }

        }

        return payment;
    }

    private boolean isLastInstallment(PaymentDefinitionDTO dto, int i) {
        return i + 1 == dto.getInstallments();
    }
}
