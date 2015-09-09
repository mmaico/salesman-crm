package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLogActivityItem is a Querydsl query type for LogActivityItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLogActivityItem extends EntityPathBase<LogActivityItem> {

    private static final long serialVersionUID = -449863238L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogActivityItem logActivityItem = new QLogActivityItem("logActivityItem");

    public final QTimelineItem _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    //inherited
    public final StringPath description;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum> type = createEnum("type", br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum.class);

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUser user;

    public QLogActivityItem(String variable) {
        this(LogActivityItem.class, forVariable(variable), INITS);
    }

    public QLogActivityItem(Path<? extends LogActivityItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLogActivityItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLogActivityItem(PathMetadata<?> metadata, PathInits inits) {
        this(LogActivityItem.class, metadata, inits);
    }

    public QLogActivityItem(Class<? extends LogActivityItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineItem(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

