package br.com.kproj.salesman.infrastructure.entity.activities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubPersonalActvityEntity is a Querydsl query type for SubPersonalActvityEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubPersonalActvityEntity extends EntityPathBase<SubPersonalActvityEntity> {

    private static final long serialVersionUID = 77060703L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubPersonalActvityEntity subPersonalActvityEntity = new QSubPersonalActvityEntity("subPersonalActvityEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QPersonalActivityEntity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRootPersonalActivityEntity parent;

    public QSubPersonalActvityEntity(String variable) {
        this(SubPersonalActvityEntity.class, forVariable(variable), INITS);
    }

    public QSubPersonalActvityEntity(Path<? extends SubPersonalActvityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubPersonalActvityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubPersonalActvityEntity(PathMetadata metadata, PathInits inits) {
        this(SubPersonalActvityEntity.class, metadata, inits);
    }

    public QSubPersonalActvityEntity(Class<? extends SubPersonalActvityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QPersonalActivityEntity(forProperty("activity"), inits.get("activity")) : null;
        this.parent = inits.isInitialized("parent") ? new QRootPersonalActivityEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

