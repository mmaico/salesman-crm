package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.querydsl.core.types.PathMetadataFactory.*;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogActivity is a Querydsl query type for LogActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogActivity extends EntityPathBase<LogActivity> {

    private static final long serialVersionUID = -136245753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogActivity logActivity = new QLogActivity("logActivity");

    public final QTimelineActivity _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    //inherited
    public final ListPath<AppFileEntity, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum> type = createEnum("type", br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum.class);

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QLogActivity(String variable) {
        this(LogActivity.class, forVariable(variable), INITS);
    }

    public QLogActivity(Path<? extends LogActivity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogActivity(PathMetadata metadata, PathInits inits) {
        this(LogActivity.class, metadata, inits);
    }

    public QLogActivity(Class<? extends LogActivity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

