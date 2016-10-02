package br.com.kproj.salesman.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChecklistTemplateEntity is a Querydsl query type for ChecklistTemplateEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChecklistTemplateEntity extends EntityPathBase<ChecklistTemplateEntity> {

    private static final long serialVersionUID = -1290278839L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChecklistTemplateEntity checklistTemplateEntity = new QChecklistTemplateEntity("checklistTemplateEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTaskTemplateEntity taskTemplate;

    public QChecklistTemplateEntity(String variable) {
        this(ChecklistTemplateEntity.class, forVariable(variable), INITS);
    }

    public QChecklistTemplateEntity(Path<? extends ChecklistTemplateEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistTemplateEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistTemplateEntity(PathMetadata<?> metadata, PathInits inits) {
        this(ChecklistTemplateEntity.class, metadata, inits);
    }

    public QChecklistTemplateEntity(Class<? extends ChecklistTemplateEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskTemplate = inits.isInitialized("taskTemplate") ? new QTaskTemplateEntity(forProperty("taskTemplate"), inits.get("taskTemplate")) : null;
    }

}

