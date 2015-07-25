package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="proposal_payment_item")
public class ProposalPaymentItem extends Identifiable {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull
    private Date dateDue;

    @NotNull
    private BigDecimal value;

    private String observation;

    @ManyToOne
    private BusinessProposal businessProposal;

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
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

    public BusinessProposal getBusinessProposal() {
        return businessProposal;
    }

    public void setBusinessProposal(BusinessProposal businessProposal) {
        this.businessProposal = businessProposal;
    }
}
