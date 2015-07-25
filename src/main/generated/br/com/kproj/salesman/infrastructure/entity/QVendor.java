package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVendor is a Querydsl query type for Vendor
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVendor extends EntityPathBase<Vendor> {

    private static final long serialVersionUID = -1029195721L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVendor vendor = new QVendor("vendor");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final CollectionPath<br.com.kproj.salesman.infrastructure.entity.person.Person, br.com.kproj.salesman.infrastructure.entity.person.QPerson> clients = this.<br.com.kproj.salesman.infrastructure.entity.person.Person, br.com.kproj.salesman.infrastructure.entity.person.QPerson>createCollection("clients", br.com.kproj.salesman.infrastructure.entity.person.Person.class, br.com.kproj.salesman.infrastructure.entity.person.QPerson.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final CollectionPath<Service, QService> saleableProjects = this.<Service, QService>createCollection("saleableProjects", Service.class, QService.class, PathInits.DIRECT2);

    public final QUser user;

    public QVendor(String variable) {
        this(Vendor.class, forVariable(variable), INITS);
    }

    public QVendor(Path<? extends Vendor> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVendor(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVendor(PathMetadata<?> metadata, PathInits inits) {
        this(Vendor.class, metadata, inits);
    }

    public QVendor(Class<? extends Vendor> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

