package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItemEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("saleableItemRepositoryNegotiatedSaleableModule")
public interface SaleableItemRepositorySpringData extends BaseRepositoryLegacy<ProposalSaleableItemEntity, Long> {

    @Query("SELECT item FROM ProposalSaleableItemEntity AS item WHERE item.negotiated.id = :negotiatedId")
    Collection<ProposalSaleableItemEntity> findAll(@Param("negotiatedId") Long negotiatedId);

    @Query("SELECT CASE WHEN count(item) > 0 THEN true ELSE false END FROM ProposalSaleableItemEntity AS item " +
            " WHERE item.negotiated.id = :negotiatedId AND item.saleable.id = :saleableId AND item.usedPackage = null")
    Boolean alreadyExists(@Param("negotiatedId") Long negotiatedId, @Param("saleableId") Long saleableId);

    @Query("SELECT CASE WHEN count(item) > 0 THEN true ELSE false END FROM ProposalSaleableItemEntity AS item " +
            " WHERE item.negotiated.id = :negotiatedId AND item.saleable.id = :saleableId AND item.usedPackage.id = :packageId")
    Boolean alreadyExists(@Param("negotiatedId") Long negotiatedId, @Param("saleableId") Long saleableId, @Param("packageId") Long packageId);

}
