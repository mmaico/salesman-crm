package br.com.kproj.salesman.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContactEntity is a Querydsl query type for ContactEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContactEntity extends EntityPathBase<ContactEntity> {

    private static final long serialVersionUID = -1924272556L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContactEntity contactEntity = new QContactEntity("contactEntity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.accounts.QCustomerEntity customer;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath position = createString("position");

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public QContactEntity(String variable) {
        this(ContactEntity.class, forVariable(variable), INITS);
    }

    public QContactEntity(Path<? extends ContactEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContactEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContactEntity(PathMetadata metadata, PathInits inits) {
        this(ContactEntity.class, metadata, inits);
    }

    public QContactEntity(Class<? extends ContactEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new br.com.kproj.salesman.infrastructure.entity.accounts.QCustomerEntity(forProperty("customer")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

