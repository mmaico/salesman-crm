package br.com.kproj.salesman.infrastructure.entity.proposal;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "business_proposal")
public class BusinessProposal extends Identifiable {

    @ManyToOne
    @JoinColumn(name="person_id")
    @NotNull(message = "business.proposal.person.required")
    private Person person;

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull(message = "business.proposal.vendor.required")
    private User vendor;

    private String careOf;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date deliveryForeCast;

    private String introduction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessProposal")
    @Valid
    private List<ProposalProductItem> productItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessProposal")
    @Valid
    private List<ProposalPaymentItem> paymentItems;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public Date getDeliveryForeCast() {
        return deliveryForeCast;
    }

    public void setDeliveryForeCast(Date deliveryForeCast) {
        this.deliveryForeCast = deliveryForeCast;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<ProposalProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProposalProductItem> productItems) {
        this.productItems = productItems;
    }

    public List<ProposalPaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<ProposalPaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }


}
