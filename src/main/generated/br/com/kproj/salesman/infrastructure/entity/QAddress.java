package br.com.kproj.salesman.infrastructure.entity;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = 959506149L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.location.QCity city;

    public final StringPath complement = createString("complement");

    public final br.com.kproj.salesman.infrastructure.entity.location.QCountry country;

    public final StringPath district = createString("district");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath number = createString("number");

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson person;

    public final StringPath postalCode = createString("postalCode");

    public final br.com.kproj.salesman.infrastructure.entity.location.QState state;

    public final StringPath street = createString("street");

    public final EnumPath<Address.Type> type = createEnum("type", Address.Type.class);

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAddress(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAddress(PathMetadata<?> metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new br.com.kproj.salesman.infrastructure.entity.location.QCity(forProperty("city")) : null;
        this.country = inits.isInitialized("country") ? new br.com.kproj.salesman.infrastructure.entity.location.QCountry(forProperty("country")) : null;
        this.person = inits.isInitialized("person") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("person"), inits.get("person")) : null;
        this.state = inits.isInitialized("state") ? new br.com.kproj.salesman.infrastructure.entity.location.QState(forProperty("state")) : null;
    }

}

