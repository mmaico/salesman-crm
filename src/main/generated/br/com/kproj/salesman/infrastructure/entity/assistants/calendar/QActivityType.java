package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QActivityType is a Querydsl query type for ActivityType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivityType extends EntityPathBase<ActivityType> {

    private static final long serialVersionUID = -1638869521L;

    public static final QActivityType activityType = new QActivityType("activityType");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QActivityType(String variable) {
        super(ActivityType.class, forVariable(variable));
    }

    public QActivityType(Path<? extends ActivityType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivityType(PathMetadata<?> metadata) {
        super(ActivityType.class, metadata);
    }

}

