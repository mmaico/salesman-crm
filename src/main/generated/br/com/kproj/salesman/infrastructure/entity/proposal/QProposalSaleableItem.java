package br.com.kproj.salesman.infrastructure.entity.proposal;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProposalSaleableItem is a Querydsl query type for ProposalSaleableItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProposalSaleableItem extends EntityPathBase<ProposalSaleableItem> {

    private static final long serialVersionUID = -46078253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProposalSaleableItem proposalSaleableItem = new QProposalSaleableItem("proposalSaleableItem");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QBusinessProposalEntity businessProposalEntity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> originalPrice = createNumber("originalPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity saleableUnit;

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSalePackageEntity salePackage;

    public QProposalSaleableItem(String variable) {
        this(ProposalSaleableItem.class, forVariable(variable), INITS);
    }

    public QProposalSaleableItem(Path<? extends ProposalSaleableItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProposalSaleableItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProposalSaleableItem(PathMetadata metadata, PathInits inits) {
        this(ProposalSaleableItem.class, metadata, inits);
    }

    public QProposalSaleableItem(Class<? extends ProposalSaleableItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessProposalEntity = inits.isInitialized("businessProposalEntity") ? new QBusinessProposalEntity(forProperty("businessProposalEntity"), inits.get("businessProposalEntity")) : null;
        this.saleableUnit = inits.isInitialized("saleableUnit") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity(forProperty("saleableUnit")) : null;
        this.salePackage = inits.isInitialized("salePackage") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSalePackageEntity(forProperty("salePackage"), inits.get("salePackage")) : null;
    }

}

