package br.com.kproj.salesman.infrastructure.entity.proposal;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProposalPaymentItem is a Querydsl query type for ProposalPaymentItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProposalPaymentItem extends EntityPathBase<ProposalPaymentItem> {

    private static final long serialVersionUID = 566385754L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProposalPaymentItem proposalPaymentItem = new QProposalPaymentItem("proposalPaymentItem");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QBusinessProposalEntity businessProposalEntity;

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath observation = createString("observation");

    public final NumberPath<java.math.BigDecimal> value = createNumber("value", java.math.BigDecimal.class);

    public QProposalPaymentItem(String variable) {
        this(ProposalPaymentItem.class, forVariable(variable), INITS);
    }

    public QProposalPaymentItem(Path<? extends ProposalPaymentItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProposalPaymentItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProposalPaymentItem(PathMetadata metadata, PathInits inits) {
        this(ProposalPaymentItem.class, metadata, inits);
    }

    public QProposalPaymentItem(Class<? extends ProposalPaymentItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessProposalEntity = inits.isInitialized("businessProposalEntity") ? new QBusinessProposalEntity(forProperty("businessProposalEntity"), inits.get("businessProposalEntity")) : null;
    }

}

