package br.com.kproj.salesman.infrastructure.entity.proposal;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBusinessProposal is a Querydsl query type for BusinessProposal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBusinessProposal extends EntityPathBase<BusinessProposalEntity> {

    private static final long serialVersionUID = -1363083457L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessProposal businessProposal = new QBusinessProposal("businessProposal");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final StringPath careOf = createString("careOf");

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson client;

    public final DateTimePath<java.util.Date> deliveryForeCast = createDateTime("deliveryForeCast", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegion operationRegion;

    public final ListPath<ProposalPaymentItem, QProposalPaymentItem> paymentItems = this.<ProposalPaymentItem, QProposalPaymentItem>createList("paymentItems", ProposalPaymentItem.class, QProposalPaymentItem.class, PathInits.DIRECT2);

    public final ListPath<ProposalSaleableItem, QProposalSaleableItem> saleableItems = this.<ProposalSaleableItem, QProposalSaleableItem>createList("saleableItems", ProposalSaleableItem.class, QProposalSaleableItem.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity seller;

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature> temperature = createEnum("temperature", br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public QBusinessProposal(String variable) {
        this(BusinessProposalEntity.class, forVariable(variable), INITS);
    }

    public QBusinessProposal(Path<? extends BusinessProposalEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposal(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposal(PathMetadata<?> metadata, PathInits inits) {
        this(BusinessProposalEntity.class, metadata, inits);
    }

    public QBusinessProposal(Class<? extends BusinessProposalEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("client"), inits.get("client")) : null;
        this.operationRegion = inits.isInitialized("operationRegion") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegion(forProperty("operationRegion")) : null;
        this.seller = inits.isInitialized("seller") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("seller"), inits.get("seller")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

