package br.com.kproj.salesman.infrastructure.entity.proposal;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessProposalEntity is a Querydsl query type for BusinessProposalEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusinessProposalEntity extends EntityPathBase<BusinessProposalEntity> {

    private static final long serialVersionUID = 1953571842L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessProposalEntity businessProposalEntity = new QBusinessProposalEntity("businessProposalEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<java.math.BigDecimal> ammountPayable = createNumber("ammountPayable", java.math.BigDecimal.class);

    public final StringPath careOf = createString("careOf");

    public final br.com.kproj.salesman.infrastructure.entity.accounts.QCustomerEntity customer;

    public final DateTimePath<java.util.Date> deliveryForeCast = createDateTime("deliveryForeCast", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> discount = createNumber("discount", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final ListPath<ProposalPaymentItem, QProposalPaymentItem> paymentItems = this.<ProposalPaymentItem, QProposalPaymentItem>createList("paymentItems", ProposalPaymentItem.class, QProposalPaymentItem.class, PathInits.DIRECT2);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature> proposalTemperature = createEnum("proposalTemperature", br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity region;

    public final ListPath<ProposalSaleableItem, QProposalSaleableItem> saleableItems = this.<ProposalSaleableItem, QProposalSaleableItem>createList("saleableItems", ProposalSaleableItem.class, QProposalSaleableItem.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity seller;

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public QBusinessProposalEntity(String variable) {
        this(BusinessProposalEntity.class, forVariable(variable), INITS);
    }

    public QBusinessProposalEntity(Path<? extends BusinessProposalEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessProposalEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessProposalEntity(PathMetadata metadata, PathInits inits) {
        this(BusinessProposalEntity.class, metadata, inits);
    }

    public QBusinessProposalEntity(Class<? extends BusinessProposalEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new br.com.kproj.salesman.infrastructure.entity.accounts.QCustomerEntity(forProperty("customer")) : null;
        this.region = inits.isInitialized("region") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity(forProperty("region")) : null;
        this.seller = inits.isInitialized("seller") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("seller"), inits.get("seller")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

