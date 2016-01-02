package br.com.kproj.salesman.negotiation.view.dto;

import java.math.BigDecimal;
import java.util.Date;


public class InstallmentDTO {

    private Date dueDate;
    private BigDecimal value;

    public InstallmentDTO(){}

    public InstallmentDTO(Date dueDate, BigDecimal value) {
        this.dueDate = dueDate;
        this.value = value;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
