package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubtaskEntity is a Querydsl query type for SubtaskEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubtaskEntity extends EntityPathBase<SubtaskEntity> {

    private static final long serialVersionUID = 2020439950L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubtaskEntity subtaskEntity = new QSubtaskEntity("subtaskEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRootTaskEntity parent;

    public final QTaskEntity task;

    public QSubtaskEntity(String variable) {
        this(SubtaskEntity.class, forVariable(variable), INITS);
    }

    public QSubtaskEntity(Path<? extends SubtaskEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubtaskEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubtaskEntity(PathMetadata metadata, PathInits inits) {
        this(SubtaskEntity.class, metadata, inits);
    }

    public QSubtaskEntity(Class<? extends SubtaskEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QRootTaskEntity(forProperty("parent"), inits.get("parent")) : null;
        this.task = inits.isInitialized("task") ? new QTaskEntity(forProperty("task"), inits.get("task")) : null;
    }

}

