package br.com.kproj.salesman.infrastructure.entity.proposal;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Entity
@Table(name = "business_proposal")
public class BusinessProposalEntity extends Identifiable implements TimelinePresent {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3466031805155434986L;

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
    @JoinColumn(name="account_id")
    @NotNull(message = "business.proposal.account.required")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name="seller_id")
    @NotNull(message = "business.proposal.seller.required")
    private UserEntity seller;

    private String careOf;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date deliveryForeCast;

    private String introduction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessProposalEntity")
    private List<ProposalSaleableItem> saleableItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessProposalEntity")
    private List<ProposalPaymentItem> paymentItems;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "business.proposal.region.required")
    private OperationRegionEntity operationRegionEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "temperature")
    private ProposalTemperature temperature;

    @OneToOne(mappedBy = "proposal")
    private Timeline timeline;


    public BusinessProposalEntity(){}
    public BusinessProposalEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
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

    public OperationRegionEntity getOperationRegionEntity() {
        return operationRegionEntity;
    }

    public void setOperationRegionEntity(OperationRegionEntity operationRegionEntity) {
        this.operationRegionEntity = operationRegionEntity;
    }

    public List<ProposalSaleableItem> getSaleableItems() {
        return saleableItems;
    }

    public void setSaleableItems(List<ProposalSaleableItem> saleableItems) {
        this.saleableItems = saleableItems;
    }

    public ProposalTemperature getTemperature() {
        return temperature;
    }

    public void setTemperature(ProposalTemperature temperature) {
        this.temperature = temperature;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
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
        item.setBusinessProposalEntity(this);
        this.getPaymentItems().add(item);
    }

    public void addNewProposalSaleableItem(ProposalSaleableItem saleableItem) {
        saleableItem.setBusinessProposalEntity(this);
        this.getSaleableItems().add(saleableItem);
    }


}
