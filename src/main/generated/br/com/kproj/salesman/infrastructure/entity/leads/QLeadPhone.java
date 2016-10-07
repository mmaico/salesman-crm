package br.com.kproj.salesman.infrastructure.entity.leads;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeadPhone is a Querydsl query type for LeadPhone
 */
@Generated("com.querydsl.codegen.EntitySerializer")
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
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeadPhone(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeadPhone(PathMetadata metadata, PathInits inits) {
        this(LeadPhone.class, metadata, inits);
    }

    public QLeadPhone(Class<? extends LeadPhone> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lead = inits.isInitialized("lead") ? new QLeadEntity(forProperty("lead"), inits.get("lead")) : null;
    }

}

