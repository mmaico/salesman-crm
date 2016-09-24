package br.com.kproj.salesman.infrastructure.entity.leads;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QLeadAddress is a Querydsl query type for LeadAddress
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLeadAddress extends EntityPathBase<LeadAddress> {

    private static final long serialVersionUID = -1209849902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeadAddress leadAddress = new QLeadAddress("leadAddress");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath district = createString("district");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLead lead;

    public final StringPath number = createString("number");

    public final StringPath street = createString("street");

    public final StringPath zipcode = createString("zipcode");

    public QLeadAddress(String variable) {
        this(LeadAddress.class, forVariable(variable), INITS);
    }

    public QLeadAddress(Path<? extends LeadAddress> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeadAddress(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLeadAddress(PathMetadata<?> metadata, PathInits inits) {
        this(LeadAddress.class, metadata, inits);
    }

    public QLeadAddress(Class<? extends LeadAddress> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lead = inits.isInitialized("lead") ? new QLead(forProperty("lead"), inits.get("lead")) : null;
    }

}

