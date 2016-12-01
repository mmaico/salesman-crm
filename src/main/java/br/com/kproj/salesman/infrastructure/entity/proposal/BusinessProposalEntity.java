package br.com.kproj.salesman.infrastructure.entity.proposal;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @JoinColumn(name="customer_id")
    @NotNull(message = "business.proposal.account.required")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private UserEntity seller;

    @Column(name="care_of")
    private String careOf;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @Column(name="delivery_forecast")
    private Date deliveryForeCast;

    private String introduction;

    @OneToMany(mappedBy = "businessProposal")
    private List<BusinessProposalItemEntity> saleableItems;

    @OneToMany(mappedBy = "businessProposal")
    private List<ProposalPaymentItem> paymentItems;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    private OperationRegionEntity region;

    @Enumerated(EnumType.STRING)
    @Column(name = "temperature")
    private ProposalTemperature proposalTemperature;

    private BigDecimal discount;

    @Column(name = "ammount_payable")
    private BigDecimal ammountPayable;

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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
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

    public OperationRegionEntity getRegion() {
        return region;
    }

    public void setRegion(OperationRegionEntity region) {
        this.region = region;
    }

    public List<BusinessProposalItemEntity> getSaleableItems() {
        return saleableItems;
    }

    public void setSaleableItems(List<BusinessProposalItemEntity> saleableItems) {
        this.saleableItems = saleableItems;
    }

    public ProposalTemperature getProposalTemperature() {
        return proposalTemperature;
    }

    public void setProposalTemperature(ProposalTemperature proposalTemperature) {
        this.proposalTemperature = proposalTemperature;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmmountPayable() {
        return ammountPayable;
    }

    public void setAmmountPayable(BigDecimal ammountPayable) {
        this.ammountPayable = ammountPayable;
    }

    public Long getRegionId() {
        return this.region == null ? null : this.region.getId();
    }

    public Long getSellerId() {
        return this.seller == null ? null : this.seller.getId();
    }

    public Long getCustomerId() {
        return this.customer == null ? null : this.customer.getId();
    }

    public Long getTimelineId() {
        return this.timeline == null ? null : this.timeline.getId();
    }
}
