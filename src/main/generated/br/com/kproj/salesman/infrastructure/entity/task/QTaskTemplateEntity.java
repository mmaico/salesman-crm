package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskTemplateEntity is a Querydsl query type for TaskTemplateEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskTemplateEntity extends EntityPathBase<TaskTemplateEntity> {

    private static final long serialVersionUID = -1083501028L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskTemplateEntity taskTemplateEntity = new QTaskTemplateEntity("taskTemplateEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<ChecklistTemplateEntity, QChecklistTemplateEntity> checklistTemplateEntities = this.<ChecklistTemplateEntity, QChecklistTemplateEntity>createList("checklistTemplateEntities", ChecklistTemplateEntity.class, QChecklistTemplateEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = this.<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile>createList("files", br.com.kproj.salesman.infrastructure.entity.AppFile.class, br.com.kproj.salesman.infrastructure.entity.QAppFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final NumberPath<Integer> quantityDaysTofinishAfertSignedContract = createNumber("quantityDaysTofinishAfertSignedContract", Integer.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity region;

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity saleable;

    public final ListPath<TaskCostTemplate, QTaskCostTemplate> tasksCostsTemplates = this.<TaskCostTemplate, QTaskCostTemplate>createList("tasksCostsTemplates", TaskCostTemplate.class, QTaskCostTemplate.class, PathInits.DIRECT2);

    public final ListPath<TaskTemplateEntity, QTaskTemplateEntity> templatesChilds = this.<TaskTemplateEntity, QTaskTemplateEntity>createList("templatesChilds", TaskTemplateEntity.class, QTaskTemplateEntity.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QTaskTemplateEntity(String variable) {
        this(TaskTemplateEntity.class, forVariable(variable), INITS);
    }

    public QTaskTemplateEntity(Path<? extends TaskTemplateEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskTemplateEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskTemplateEntity(PathMetadata metadata, PathInits inits) {
        this(TaskTemplateEntity.class, metadata, inits);
    }

    public QTaskTemplateEntity(Class<? extends TaskTemplateEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity(forProperty("region")) : null;
        this.saleable = inits.isInitialized("saleable") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity(forProperty("saleable")) : null;
    }

}

