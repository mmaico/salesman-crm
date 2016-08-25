package br.com.kproj.salesman.negotiation2.domain.model.negotiation;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation2.domain.model.account.Account;
import br.com.kproj.salesman.negotiation2.domain.model.operationregion.OperationRegion;
import br.com.kproj.salesman.negotiation2.domain.model.payment.InstallmentItem;
import br.com.kproj.salesman.negotiation2.domain.model.seller.Seller;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


public class Negotiation extends ModelIdentifiable {

    private Long id;

    private Account account;

    private Seller seller;

    private OperationRegion operationRegion;

    @DateTimeFormat(pattern = "dd/M/Y")
    private Date deliveryForeCast;

    private String introduction = StringUtils.EMPTY;

    private String careOf = StringUtils.EMPTY;

    private List<SaleableItem> saleablesItems = Lists.newArrayList();

    private List<InstallmentItem> installments = Lists.newArrayList();




    public boolean temperatureIsClosedWon() {
        return Boolean.FALSE;
    }















    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
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

    public List<SaleableItem> getSaleablesItems() {
        return saleablesItems;
    }

    public void setSaleablesItems(List<SaleableItem> saleablesItems) {
        this.saleablesItems = saleablesItems;
    }

    public List<InstallmentItem> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentItem> installments) {
        this.installments = installments;
    }

    public OperationRegion getOperationRegion() {
        return operationRegion;
    }

    public void setOperationRegion(OperationRegion operationRegion) {
        this.operationRegion = operationRegion;
    }


}
