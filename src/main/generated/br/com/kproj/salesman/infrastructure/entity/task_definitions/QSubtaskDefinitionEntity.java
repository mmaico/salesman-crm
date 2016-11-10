package br.com.kproj.salesman.infrastructure.entity.task_definitions;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubtaskDefinitionEntity is a Querydsl query type for SubtaskDefinitionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubtaskDefinitionEntity extends EntityPathBase<SubtaskDefinitionEntity> {

    private static final long serialVersionUID = -1654122558L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubtaskDefinitionEntity subtaskDefinitionEntity = new QSubtaskDefinitionEntity("subtaskDefinitionEntity");

    public final QTaskDefinitionEntity _super = new QTaskDefinitionEntity(this);

    //inherited
    public final ListPath<ChecklistDefinitionEntity, QChecklistDefinitionEntity> checklist = _super.checklist;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QRootTaskDefinitionEntity parent;

    //inherited
    public final NumberPath<Integer> quantityDaysTofinishAfertSignedContract = _super.quantityDaysTofinishAfertSignedContract;

    //inherited
    public final StringPath title = _super.title;

    public QSubtaskDefinitionEntity(String variable) {
        this(SubtaskDefinitionEntity.class, forVariable(variable), INITS);
    }

    public QSubtaskDefinitionEntity(Path<? extends SubtaskDefinitionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubtaskDefinitionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubtaskDefinitionEntity(PathMetadata metadata, PathInits inits) {
        this(SubtaskDefinitionEntity.class, metadata, inits);
    }

    public QSubtaskDefinitionEntity(Class<? extends SubtaskDefinitionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QRootTaskDefinitionEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

