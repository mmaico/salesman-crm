package br.com.kproj.salesman.infrastructure.entity.proposal;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Entity
@Table(name = "business_proposal")
public class BusinessProposal extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3466031805155434986L;

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

    public BigDecimal getTotal() {

        if (isEmptySafe(this.getProductItems())) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = this.getProductItems()
                .stream()
                .map(e -> e.getPrice().multiply(new BigDecimal(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total;
    }

    public BigDecimal getTotalToPay() {

        if (isEmptySafe(this.getPaymentItems())) {
            return BigDecimal.ZERO;
        }

        BigDecimal totaToPay = this.getPaymentItems()
                .stream()
                .map(e -> e.getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totaToPay;
    }

    

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