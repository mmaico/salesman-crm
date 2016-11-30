package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="proposal_saleable_item")
public class ProposalSaleableItemEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="saleable_id")
    @ExcludeField
    private SaleableUnitEntity saleable;

    @ManyToOne
    @JoinColumn(name = "package_id")
    @ExcludeField
    private SalePackageEntity usedPackage;

    @ManyToOne
    @JoinColumn(name="business_proposal_item_id")
    @ExcludeField
    private BusinessProposalItemEntity negotiated;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableUnitEntity getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnitEntity saleable) {
        this.saleable = saleable;
    }

    public SalePackageEntity getUsedPackage() {
        return usedPackage;
    }

    public void setUsedPackage(SalePackageEntity usedPackage) {
        this.usedPackage = usedPackage;
    }

    public BusinessProposalItemEntity getNegotiated() {
        return negotiated;
    }

    public void setNegotiated(BusinessProposalItemEntity negotiated) {
        this.negotiated = negotiated;
    }
}
