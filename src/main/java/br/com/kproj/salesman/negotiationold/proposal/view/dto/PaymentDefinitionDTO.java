package br.com.kproj.salesman.negotiationold.proposal.view.dto;


import org.joda.time.DateTime;

import java.util.Date;

public class PaymentDefinitionDTO {

    private Integer installments = 1;
    private Date firstDateDue = DateTime.now().plusDays(30).toDate();
    private Integer dateIntervalBetweenDates = 30;

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Date getFirstDateDue() {
        return firstDateDue;
    }

    public void setFirstDateDue(Date firstDateDue) {
        this.firstDateDue = firstDateDue;
    }

    public Integer getDateIntervalBetweenDates() {
        return dateIntervalBetweenDates;
    }

    public void setDateIntervalBetweenDates(Integer dateIntervalBetweenDates) {
        this.dateIntervalBetweenDates = dateIntervalBetweenDates;
    }

    public static PaymentDefinitionDTO create(Integer installments, Date firstDateDue, Integer interval) {
        PaymentDefinitionDTO paymentDefinitionDTO = new PaymentDefinitionDTO();
        paymentDefinitionDTO.setDateIntervalBetweenDates(interval != null ? interval : 30);
        paymentDefinitionDTO.setFirstDateDue(firstDateDue != null ? firstDateDue : DateTime.now().plusDays(30).toDate());
        paymentDefinitionDTO.setInstallments(installments != null ? installments : 1);

        return paymentDefinitionDTO;
    }
}
