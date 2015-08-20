package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLogActivityItem is a Querydsl query type for LogActivityItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLogActivityItem extends EntityPathBase<LogActivityItem> {

    private static final long serialVersionUID = -449863238L;

    public static final QLogActivityItem logActivityItem = new QLogActivityItem("logActivityItem");

    public final QTimelineItem _super = new QTimelineItem(this);

    //inherited
    public final DateTimePath<java.util.Date> creation = _super.creation;

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = _super.files;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum> type = createEnum("type", br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum.class);

    public QLogActivityItem(String variable) {
        super(LogActivityItem.class, forVariable(variable));
    }

    public QLogActivityItem(Path<? extends LogActivityItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogActivityItem(PathMetadata<?> metadata) {
        super(LogActivityItem.class, metadata);
    }

}

