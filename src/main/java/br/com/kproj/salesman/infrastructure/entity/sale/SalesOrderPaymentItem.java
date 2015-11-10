package br.com.kproj.salesman.infrastructure.entity.sale;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name="sales_order_payment_item")
public class SalesOrderPaymentItem extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull(message = "order.duedate.is.invalid")
    @Column(name="due_date")
    private Date dueDate;

    @NotNull(message = "order.payment.value.is.invalid")
    private BigDecimal value;

    private String observation;

    @ManyToOne
    @JoinColumn(name="order_id")
    private SalesOrder salesOrder;

    @Override
    public Long getId() {
        return this.id;
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

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
