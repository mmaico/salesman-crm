package br.com.kproj.salesman.infrastructure.entity.proposal;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProposalPaymentItem is a Querydsl query type for ProposalPaymentItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProposalPaymentItem extends EntityPathBase<ProposalPaymentItem> {

    private static final long serialVersionUID = 566385754L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProposalPaymentItem proposalPaymentItem = new QProposalPaymentItem("proposalPaymentItem");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QBusinessProposal businessProposal;

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath observation = createString("observation");

    public final NumberPath<java.math.BigDecimal> value = createNumber("value", java.math.BigDecimal.class);

    public QProposalPaymentItem(String variable) {
        this(ProposalPaymentItem.class, forVariable(variable), INITS);
    }

    public QProposalPaymentItem(Path<? extends ProposalPaymentItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProposalPaymentItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProposalPaymentItem(PathMetadata<?> metadata, PathInits inits) {
        this(ProposalPaymentItem.class, metadata, inits);
    }

    public QProposalPaymentItem(Class<? extends ProposalPaymentItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessProposal = inits.isInitialized("businessProposal") ? new QBusinessProposal(forProperty("businessProposal"), inits.get("businessProposal")) : null;
    }

}

