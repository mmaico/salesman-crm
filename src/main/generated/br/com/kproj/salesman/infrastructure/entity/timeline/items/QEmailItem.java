package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmailItem is a Querydsl query type for EmailItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmailItem extends EntityPathBase<EmailItem> {

    private static final long serialVersionUID = 358971747L;

    public static final QEmailItem emailItem = new QEmailItem("emailItem");

    public final QTimelineItem _super = new QTimelineItem(this);

    //inherited
    public final DateTimePath<java.util.Date> creation = _super.creation;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = _super.files;

    public final StringPath from = createString("from");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath subject = createString("subject");

    public final StringPath to = createString("to");

    public QEmailItem(String variable) {
        super(EmailItem.class, forVariable(variable));
    }

    public QEmailItem(Path<? extends EmailItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailItem(PathMetadata<?> metadata) {
        super(EmailItem.class, metadata);
    }

}

