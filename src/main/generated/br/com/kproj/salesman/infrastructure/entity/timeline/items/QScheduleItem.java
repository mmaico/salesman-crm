package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QScheduleItem is a Querydsl query type for ScheduleItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QScheduleItem extends EntityPathBase<ScheduleItem> {

    private static final long serialVersionUID = -1372276426L;

    public static final QScheduleItem scheduleItem = new QScheduleItem("scheduleItem");

    public final QTimelineItem _super = new QTimelineItem(this);

    //inherited
    public final DateTimePath<java.util.Date> creation = _super.creation;

    //inherited
    public final StringPath description = _super.description;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = _super.files;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath title = createString("title");

    public QScheduleItem(String variable) {
        super(ScheduleItem.class, forVariable(variable));
    }

    public QScheduleItem(Path<? extends ScheduleItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScheduleItem(PathMetadata<?> metadata) {
        super(ScheduleItem.class, metadata);
    }

}

