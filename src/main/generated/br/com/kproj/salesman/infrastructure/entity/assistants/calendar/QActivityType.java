package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QActivityType is a Querydsl query type for ActivityType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
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

    public QActivityType(PathMetadata metadata) {
        super(ActivityType.class, metadata);
    }

}

