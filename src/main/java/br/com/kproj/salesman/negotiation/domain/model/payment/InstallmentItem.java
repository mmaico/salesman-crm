package br.com.kproj.salesman.negotiation.domain.model.payment;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class InstallmentItem extends ModelIdentifiable {

    private Long id;

    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull(message = "domain.payment.duedate.is.invalid")
    private Date dueDate;

    @NotNull(message = "domain.payment.value.is.invalid")
    private BigDecimal value;

    private String observation;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
