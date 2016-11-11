package br.com.kproj.salesman.infrastructure.entity.task_definitions;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskDefinitionEntity is a Querydsl query type for TaskDefinitionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskDefinitionEntity extends EntityPathBase<TaskDefinitionEntity> {

    private static final long serialVersionUID = 308373844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskDefinitionEntity taskDefinitionEntity = new QTaskDefinitionEntity("taskDefinitionEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<ChecklistDefinitionEntity, QChecklistDefinitionEntity> checklist = this.<ChecklistDefinitionEntity, QChecklistDefinitionEntity>createList("checklist", ChecklistDefinitionEntity.class, QChecklistDefinitionEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quantityDaysToFinish = createNumber("quantityDaysToFinish", Integer.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity region;

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity saleable;

    public final StringPath title = createString("title");

    public final EnumPath<TaskDefinitionTypeEntity> type = createEnum("type", TaskDefinitionTypeEntity.class);

    public QTaskDefinitionEntity(String variable) {
        this(TaskDefinitionEntity.class, forVariable(variable), INITS);
    }

    public QTaskDefinitionEntity(Path<? extends TaskDefinitionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskDefinitionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskDefinitionEntity(PathMetadata metadata, PathInits inits) {
        this(TaskDefinitionEntity.class, metadata, inits);
    }

    public QTaskDefinitionEntity(Class<? extends TaskDefinitionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity(forProperty("region")) : null;
        this.saleable = inits.isInitialized("saleable") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity(forProperty("saleable")) : null;
    }

}

