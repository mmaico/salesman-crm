package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserPosition is a Querydsl query type for UserPosition
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserPosition extends EntityPathBase<UserPosition> {

    private static final long serialVersionUID = -89009725L;

    public static final QUserPosition userPosition = new QUserPosition("userPosition");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QUserPosition(String variable) {
        super(UserPosition.class, forVariable(variable));
    }

    public QUserPosition(Path<? extends UserPosition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPosition(PathMetadata<?> metadata) {
        super(UserPosition.class, metadata);
    }

}

