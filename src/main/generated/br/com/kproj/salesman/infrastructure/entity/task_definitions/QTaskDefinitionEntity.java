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

    public static final QTaskDefinitionEntity taskDefinitionEntity = new QTaskDefinitionEntity("taskDefinitionEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<ChecklistDefinitionEntity, QChecklistDefinitionEntity> checklist = this.<ChecklistDefinitionEntity, QChecklistDefinitionEntity>createList("checklist", ChecklistDefinitionEntity.class, QChecklistDefinitionEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quantityDaysTofinishAfertSignedContract = createNumber("quantityDaysTofinishAfertSignedContract", Integer.class);

    public final StringPath title = createString("title");

    public QTaskDefinitionEntity(String variable) {
        super(TaskDefinitionEntity.class, forVariable(variable));
    }

    public QTaskDefinitionEntity(Path<? extends TaskDefinitionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskDefinitionEntity(PathMetadata metadata) {
        super(TaskDefinitionEntity.class, metadata);
    }

}

