package br.com.kproj.salesman.infrastructure.entity.activities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRootPersonalActivityEntity is a Querydsl query type for RootPersonalActivityEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRootPersonalActivityEntity extends EntityPathBase<RootPersonalActivityEntity> {

    private static final long serialVersionUID = 188713926L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRootPersonalActivityEntity rootPersonalActivityEntity = new QRootPersonalActivityEntity("rootPersonalActivityEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QPersonalActivityEntity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QRootPersonalActivityEntity(String variable) {
        this(RootPersonalActivityEntity.class, forVariable(variable), INITS);
    }

    public QRootPersonalActivityEntity(Path<? extends RootPersonalActivityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRootPersonalActivityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRootPersonalActivityEntity(PathMetadata metadata, PathInits inits) {
        this(RootPersonalActivityEntity.class, metadata, inits);
    }

    public QRootPersonalActivityEntity(Class<? extends RootPersonalActivityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QPersonalActivityEntity(forProperty("activity"), inits.get("activity")) : null;
    }

}

