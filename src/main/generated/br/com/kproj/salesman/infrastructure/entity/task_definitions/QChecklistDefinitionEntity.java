package br.com.kproj.salesman.infrastructure.entity.task_definitions;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChecklistDefinitionEntity is a Querydsl query type for ChecklistDefinitionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChecklistDefinitionEntity extends EntityPathBase<ChecklistDefinitionEntity> {

    private static final long serialVersionUID = 596090339L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChecklistDefinitionEntity checklistDefinitionEntity = new QChecklistDefinitionEntity("checklistDefinitionEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTaskDefinitionEntity taskDefinition;

    public QChecklistDefinitionEntity(String variable) {
        this(ChecklistDefinitionEntity.class, forVariable(variable), INITS);
    }

    public QChecklistDefinitionEntity(Path<? extends ChecklistDefinitionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChecklistDefinitionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChecklistDefinitionEntity(PathMetadata metadata, PathInits inits) {
        this(ChecklistDefinitionEntity.class, metadata, inits);
    }

    public QChecklistDefinitionEntity(Class<? extends ChecklistDefinitionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskDefinition = inits.isInitialized("taskDefinition") ? new QTaskDefinitionEntity(forProperty("taskDefinition"), inits.get("taskDefinition")) : null;
    }

}

