package br.com.kproj.salesman.infrastructure.entity.leads;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLeadEntity is a Querydsl query type for LeadEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLeadEntity extends EntityPathBase<LeadEntity> {

    private static final long serialVersionUID = 1055024197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeadEntity leadEntity = new QLeadEntity("leadEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<LeadAddress, QLeadAddress> addresses = this.<LeadAddress, QLeadAddress>createList("addresses", LeadAddress.class, QLeadAddress.class, PathInits.DIRECT2);

    public final StringPath company = createString("company");

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity createdBy;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<LeadPhone, QLeadPhone> phones = this.<LeadPhone, QLeadPhone>createList("phones", LeadPhone.class, QLeadPhone.class, PathInits.DIRECT2);

    public final StringPath position = createString("position");

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public QLeadEntity(String variable) {
        this(LeadEntity.class, forVariable(variable), INITS);
    }

    public QLeadEntity(Path<? extends LeadEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeadEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeadEntity(PathMetadata<?> metadata, PathInits inits) {
        this(LeadEntity.class, metadata, inits);
    }

    public QLeadEntity(Class<? extends LeadEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}
