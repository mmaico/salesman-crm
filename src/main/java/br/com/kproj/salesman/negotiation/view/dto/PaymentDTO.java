package br.com.kproj.salesman.negotiation.view.dto;


import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentDTO {

    private List<InstallmentDTO> installments = Lists.newArrayList();

    public List<InstallmentDTO> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentDTO> installments) {
        this.installments = installments;
    }

    public void addInstallment(Date dueDate, BigDecimal value) {
        installments.add(new InstallmentDTO(dueDate, value));
    }
}
