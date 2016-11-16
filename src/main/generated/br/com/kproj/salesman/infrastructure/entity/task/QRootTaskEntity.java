package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRootTaskEntity is a Querydsl query type for RootTaskEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRootTaskEntity extends EntityPathBase<RootTaskEntity> {

    private static final long serialVersionUID = 2126261636L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRootTaskEntity rootTaskEntity = new QRootTaskEntity("rootTaskEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTaskEntity task;

    public QRootTaskEntity(String variable) {
        this(RootTaskEntity.class, forVariable(variable), INITS);
    }

    public QRootTaskEntity(Path<? extends RootTaskEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRootTaskEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRootTaskEntity(PathMetadata metadata, PathInits inits) {
        this(RootTaskEntity.class, metadata, inits);
    }

    public QRootTaskEntity(Class<? extends RootTaskEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTaskEntity(forProperty("task"), inits.get("task")) : null;
    }

}

