package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTaskItem is a Querydsl query type for TaskItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskItem extends EntityPathBase<TaskItem> {

    private static final long serialVersionUID = -1437103324L;

    public static final QTaskItem taskItem = new QTaskItem("taskItem");

    public final QTimelineItem _super = new QTimelineItem(this);

    //inherited
    public final DateTimePath<java.util.Date> creation = _super.creation;

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = _super.files;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QTaskItem(String variable) {
        super(TaskItem.class, forVariable(variable));
    }

    public QTaskItem(Path<? extends TaskItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskItem(PathMetadata<?> metadata) {
        super(TaskItem.class, metadata);
    }

}

