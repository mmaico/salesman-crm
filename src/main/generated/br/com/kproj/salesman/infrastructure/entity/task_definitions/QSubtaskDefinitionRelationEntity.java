package br.com.kproj.salesman.infrastructure.entity.task_definitions;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubtaskDefinitionRelationEntity is a Querydsl query type for SubtaskDefinitionRelationEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubtaskDefinitionRelationEntity extends EntityPathBase<SubtaskDefinitionRelationEntity> {

    private static final long serialVersionUID = 1702773214L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubtaskDefinitionRelationEntity subtaskDefinitionRelationEntity = new QSubtaskDefinitionRelationEntity("subtaskDefinitionRelationEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRootTaskDefinitionEntity rootTask;

    public final QSubtaskDefinitionEntity subtask;

    public QSubtaskDefinitionRelationEntity(String variable) {
        this(SubtaskDefinitionRelationEntity.class, forVariable(variable), INITS);
    }

    public QSubtaskDefinitionRelationEntity(Path<? extends SubtaskDefinitionRelationEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubtaskDefinitionRelationEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubtaskDefinitionRelationEntity(PathMetadata metadata, PathInits inits) {
        this(SubtaskDefinitionRelationEntity.class, metadata, inits);
    }

    public QSubtaskDefinitionRelationEntity(Class<? extends SubtaskDefinitionRelationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rootTask = inits.isInitialized("rootTask") ? new QRootTaskDefinitionEntity(forProperty("rootTask"), inits.get("rootTask")) : null;
        this.subtask = inits.isInitialized("subtask") ? new QSubtaskDefinitionEntity(forProperty("subtask"), inits.get("subtask")) : null;
    }

}

