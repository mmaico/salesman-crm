package br.com.kproj.salesman.infrastructure.entity;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QContactEntity is a Querydsl query type for ContactEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContactEntity extends EntityPathBase<ContactEntity> {

    private static final long serialVersionUID = -1924272556L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContactEntity contactEntity = new QContactEntity("contactEntity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson person;

    public final StringPath phone = createString("phone");

    public final StringPath position = createString("position");

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public QContactEntity(String variable) {
        this(ContactEntity.class, forVariable(variable), INITS);
    }

    public QContactEntity(Path<? extends ContactEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContactEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContactEntity(PathMetadata<?> metadata, PathInits inits) {
        this(ContactEntity.class, metadata, inits);
    }

    public QContactEntity(Class<? extends ContactEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.person = inits.isInitialized("person") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("person"), inits.get("person")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

