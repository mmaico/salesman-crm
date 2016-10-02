package br.com.kproj.salesman.sales.application.dto;



import java.math.BigDecimal;
import java.util.Date;

public class InstallmentItemDTO {

    private Long id;

    private Date dueDate;

    private BigDecimal value;

    private String observation;

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
