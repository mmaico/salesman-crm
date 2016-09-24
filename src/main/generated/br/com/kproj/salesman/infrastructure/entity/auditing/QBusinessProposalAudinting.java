package br.com.kproj.salesman.infrastructure.entity.auditing;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QBusinessProposalAudinting is a Querydsl query type for BusinessProposalAudinting
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBusinessProposalAudinting extends EntityPathBase<BusinessProposalAudinting> {

    private static final long serialVersionUID = -1406285127L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessProposalAudinting businessProposalAudinting = new QBusinessProposalAudinting("businessProposalAudinting");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> entityId = createNumber("entityId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath info = createString("info");

    public final DateTimePath<java.util.Date> lastUpdate = createDateTime("lastUpdate", java.util.Date.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QBusinessProposalAudinting(String variable) {
        this(BusinessProposalAudinting.class, forVariable(variable), INITS);
    }

    public QBusinessProposalAudinting(Path<? extends BusinessProposalAudinting> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposalAudinting(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposalAudinting(PathMetadata<?> metadata, PathInits inits) {
        this(BusinessProposalAudinting.class, metadata, inits);
    }

    public QBusinessProposalAudinting(Class<? extends BusinessProposalAudinting> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

