package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmailActivity is a Querydsl query type for EmailActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmailActivity extends EntityPathBase<EmailActivity> {

    private static final long serialVersionUID = -524793505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmailActivity emailActivity = new QEmailActivity("emailActivity");

    public final QTimelineActivity _super;

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
    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QEmailActivity(String variable) {
        this(EmailActivity.class, forVariable(variable), INITS);
    }

    public QEmailActivity(Path<? extends EmailActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailActivity(PathMetadata<?> metadata, PathInits inits) {
        this(EmailActivity.class, metadata, inits);
    }

    public QEmailActivity(Class<? extends EmailActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

