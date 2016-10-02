package br.com.kproj.salesman.infrastructure.entity.leads;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLeadPhone is a Querydsl query type for LeadPhone
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLeadPhone extends EntityPathBase<LeadPhone> {

    private static final long serialVersionUID = 1429481676L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeadPhone leadPhone = new QLeadPhone("leadPhone");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLeadEntity lead;

    public final StringPath phone = createString("phone");

    public final EnumPath<LeadPhone.PhoneType> type = createEnum("type", LeadPhone.PhoneType.class);

    public QLeadPhone(String variable) {
        this(LeadPhone.class, forVariable(variable), INITS);
    }

    public QLeadPhone(Path<? extends LeadPhone> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeadPhone(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeadPhone(PathMetadata<?> metadata, PathInits inits) {
        this(LeadPhone.class, metadata, inits);
    }

    public QLeadPhone(Class<? extends LeadPhone> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lead = inits.isInitialized("lead") ? new QLeadEntity(forProperty("lead"), inits.get("lead")) : null;
    }

}

