package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskResponsibleEntity is a Querydsl query type for TaskResponsibleEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskResponsibleEntity extends EntityPathBase<TaskResponsibleEntity> {

    private static final long serialVersionUID = 421478650L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskResponsibleEntity taskResponsibleEntity = new QTaskResponsibleEntity("taskResponsibleEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTaskEntity task;

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QTaskResponsibleEntity(String variable) {
        this(TaskResponsibleEntity.class, forVariable(variable), INITS);
    }

    public QTaskResponsibleEntity(Path<? extends TaskResponsibleEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskResponsibleEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskResponsibleEntity(PathMetadata metadata, PathInits inits) {
        this(TaskResponsibleEntity.class, metadata, inits);
    }

    public QTaskResponsibleEntity(Class<? extends TaskResponsibleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTaskEntity(forProperty("task"), inits.get("task")) : null;
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

