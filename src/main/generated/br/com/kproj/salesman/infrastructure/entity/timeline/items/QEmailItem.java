package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmailItem is a Querydsl query type for EmailItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmailItem extends EntityPathBase<EmailItem> {

    private static final long serialVersionUID = 358971747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmailItem emailItem = new QEmailItem("emailItem");

    public final QTimelineItem _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

    public final StringPath from = createString("from");

    //inherited
    public final NumberPath<Long> id;

    public final StringPath subject = createString("subject");

    public final StringPath to = createString("to");

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUser user;

    public QEmailItem(String variable) {
        this(EmailItem.class, forVariable(variable), INITS);
    }

    public QEmailItem(Path<? extends EmailItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailItem(PathMetadata<?> metadata, PathInits inits) {
        this(EmailItem.class, metadata, inits);
    }

    public QEmailItem(Class<? extends EmailItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineItem(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

