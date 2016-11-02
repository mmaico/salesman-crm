package br.com.kproj.salesman.infrastructure.entity.leads;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeadAddress is a Querydsl query type for LeadAddress
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLeadAddress extends EntityPathBase<LeadAddress> {

    private static final long serialVersionUID = -1209849902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeadAddress leadAddress = new QLeadAddress("leadAddress");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath district = createString("district");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLeadEntity lead;

    public final StringPath number = createString("number");

    public final StringPath street = createString("street");

    public final StringPath zipcode = createString("zipcode");

    public QLeadAddress(String variable) {
        this(LeadAddress.class, forVariable(variable), INITS);
    }

    public QLeadAddress(Path<? extends LeadAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeadAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeadAddress(PathMetadata metadata, PathInits inits) {
        this(LeadAddress.class, metadata, inits);
    }

    public QLeadAddress(Class<? extends LeadAddress> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lead = inits.isInitialized("lead") ? new QLeadEntity(forProperty("lead"), inits.get("lead")) : null;
    }

}

