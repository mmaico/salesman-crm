package br.com.kproj.salesman.sales.view.dtos.negotiation;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public class NegotiationDTO extends ModelIdentifiable {

    private Long id;

    private Date deliveryForeCast;

    private String introduction = StringUtils.EMPTY;

    private String careOf = StringUtils.EMPTY;

    private SellerDTO seller;

    private RegionDTO region;

    private AccountDTO account;

    private List<SaleableItemDTO> saleablesItems;

    private List<InstallmentItemDTO> installments;



    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public SellerDTO getSeller() {
        return seller;
    }

    public void setSeller(SellerDTO seller) {
        this.seller = seller;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public List<SaleableItemDTO> getSaleablesItems() {
        return saleablesItems;
    }

    public void setSaleablesItems(List<SaleableItemDTO> saleablesItems) {
        this.saleablesItems = saleablesItems;
    }

    public List<InstallmentItemDTO> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentItemDTO> installments) {
        this.installments = installments;
    }


    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
