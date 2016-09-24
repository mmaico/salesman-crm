package br.com.kproj.salesman.infrastructure.entity.task;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QChecklistTemplate is a Querydsl query type for ChecklistTemplate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChecklistTemplate extends EntityPathBase<ChecklistTemplateEntity> {

    private static final long serialVersionUID = 298206406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChecklistTemplate checklistTemplate = new QChecklistTemplate("checklistTemplate");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTaskTemplate taskTemplate;

    public QChecklistTemplate(String variable) {
        this(ChecklistTemplateEntity.class, forVariable(variable), INITS);
    }

    public QChecklistTemplate(Path<? extends ChecklistTemplateEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistTemplate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistTemplate(PathMetadata<?> metadata, PathInits inits) {
        this(ChecklistTemplateEntity.class, metadata, inits);
    }

    public QChecklistTemplate(Class<? extends ChecklistTemplateEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskTemplate = inits.isInitialized("taskTemplate") ? new QTaskTemplate(forProperty("taskTemplate"), inits.get("taskTemplate")) : null;
    }

}

