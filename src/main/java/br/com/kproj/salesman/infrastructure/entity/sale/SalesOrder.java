package br.com.kproj.salesman.infrastructure.entity.sale;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Entity
@Table(name = "sales_order")
public class SalesOrder extends Identifiable {

    /**
	 *
	 */
	private static final long serialVersionUID = -3466031805155434986L;

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
    @JoinColumn(name="person_id")
    @NotNull(message = "order.person.required")
    private Person client;

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull(message = "order.vendor.required")
    private User vendor;

    @OneToOne
    @JoinColumn(name="proposal_id")
    @NotNull(message = "order.proposal.required")
    private BusinessProposal proposal;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date deliveryForecast;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesOrder")
    @Valid
    private List<SalesOrderItem> saleableItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesOrder")
    @Valid
    private List<SalesOrderPaymentItem> paymentItems;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "order.region.required")
    private OperationRegion operationRegion;


    public SalesOrder(){}
    public SalesOrder(Long id) {
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

        BigDecimal total = this.getSaleableItems()
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

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    public Date getDeliveryForecast() {
        return deliveryForecast;
    }

    public void setDeliveryForecast(Date deliveryForecast) {
        this.deliveryForecast = deliveryForecast;
    }

    public List<SalesOrderPaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<SalesOrderPaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public OperationRegion getOperationRegion() {
        return operationRegion;
    }

    public void setOperationRegion(OperationRegion operationRegion) {
        this.operationRegion = operationRegion;
    }

    public List<SalesOrderItem> getSaleableItems() {
        return saleableItems;
    }

    public void setSaleableItems(List<SalesOrderItem> saleableItems) {
        this.saleableItems = saleableItems;
    }

    public BusinessProposal getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposal proposal) {
        this.proposal = proposal;
    }
}
