package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.querydsl.core.types.PathMetadataFactory.*;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmailActivity is a Querydsl query type for EmailActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
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
    public final ListPath<AppFileEntity, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

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
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmailActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmailActivity(PathMetadata metadata, PathInits inits) {
        this(EmailActivity.class, metadata, inits);
    }

    public QEmailActivity(Class<? extends EmailActivity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

