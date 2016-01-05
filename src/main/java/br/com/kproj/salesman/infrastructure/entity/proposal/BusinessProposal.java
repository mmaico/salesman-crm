package br.com.kproj.salesman.infrastructure.entity.proposal;

import br.com.kproj.salesman.auditing.infrastructure.ExcludeAuditingField;
import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.SaleTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.ProposalCalcTotalSaleableItems;
import com.google.gson.annotations.Expose;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Entity
@Table(name = "business_proposal")
public class BusinessProposal extends Identifiable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3466031805155434986L;

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
    @JoinColumn(name="client_id")
    @NotNull(message = "business.proposal.person.required")
    private Person client;

    @ManyToOne
    @JoinColumn(name="seller_id")
    @NotNull(message = "business.proposal.seller.required")
    private User seller;

    private String careOf;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date deliveryForeCast;

    private String introduction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessProposal")
    private List<ProposalSaleableItem> saleableItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessProposal")
    private List<ProposalPaymentItem> paymentItems;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "business.proposal.region.required")
    private OperationRegion operationRegion;

    @Enumerated(EnumType.STRING)
    @Column(name = "temperature")
    private SaleTemperature temperature;


    public BusinessProposal(){}
    public BusinessProposal(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {

        if (isEmptySafe(this.getSaleableItems())) {
            return BigDecimal.ZERO;
        }

        ProposalCalcTotalSaleableItems calculator = ServiceLocator.getBean(ProposalCalcTotalSaleableItems.class);

        return calculator.getTotal(this.getSaleableItems());
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

    

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
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

    public List<ProposalPaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<ProposalPaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public OperationRegion getOperationRegion() {
        return operationRegion;
    }

    public void setOperationRegion(OperationRegion operationRegion) {
        this.operationRegion = operationRegion;
    }

    public List<ProposalSaleableItem> getSaleableItems() {
        return saleableItems;
    }

    public void setSaleableItems(List<ProposalSaleableItem> saleableItems) {
        this.saleableItems = saleableItems;
    }

    public SaleTemperature getTemperature() {
        return temperature;
    }

    public void setTemperature(SaleTemperature temperature) {
        this.temperature = temperature;
    }

    public void updateSaleableItem(ProposalSaleableItem saleableWithNewValues) {
        Optional<ProposalSaleableItem> result = this.getSaleableItems()
                .stream().filter(item -> item.getId() != null && item.getId().equals(saleableWithNewValues.getId())).findFirst();

        if(result.isPresent()) {
            ProposalSaleableItem oldItem = result.get();
            oldItem.setPrice(saleableWithNewValues.getPrice());
            oldItem.setQuantity(saleableWithNewValues.getQuantity());
        }
    }

    public void addNewProposalPaymentItem(ProposalPaymentItem item) {
        item.setBusinessProposal(this);
        this.getPaymentItems().add(item);
    }

    public void addNewProposalSaleableItem(ProposalSaleableItem saleableItem) {
        saleableItem.setBusinessProposal(this);
        this.getSaleableItems().add(saleableItem);
    }


}
