package br.com.kproj.salesman.infrastructure.entity.person;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = -1167361409L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerson person = new QPerson("person");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final BooleanPath active = createBoolean("active");

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.Address, br.com.kproj.salesman.infrastructure.entity.QAddress> addresses = this.<br.com.kproj.salesman.infrastructure.entity.Address, br.com.kproj.salesman.infrastructure.entity.QAddress>createList("addresses", br.com.kproj.salesman.infrastructure.entity.Address.class, br.com.kproj.salesman.infrastructure.entity.QAddress.class, PathInits.DIRECT2);

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.Contact, br.com.kproj.salesman.infrastructure.entity.QContact> contacts = this.<br.com.kproj.salesman.infrastructure.entity.Contact, br.com.kproj.salesman.infrastructure.entity.QContact>createList("contacts", br.com.kproj.salesman.infrastructure.entity.Contact.class, br.com.kproj.salesman.infrastructure.entity.QContact.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QPersonProfile profile;

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public QPerson(String variable) {
        this(Person.class, forVariable(variable), INITS);
    }

    public QPerson(Path<? extends Person> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPerson(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPerson(PathMetadata<?> metadata, PathInits inits) {
        this(Person.class, metadata, inits);
    }

    public QPerson(Class<? extends Person> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QPersonProfile(forProperty("profile")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

