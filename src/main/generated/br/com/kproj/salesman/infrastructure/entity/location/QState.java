package br.com.kproj.salesman.infrastructure.entity.location;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QState is a Querydsl query type for State
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QState extends EntityPathBase<State> {

    private static final long serialVersionUID = -2065765177L;

    public static final QState state = new QState("state");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QState(String variable) {
        super(State.class, forVariable(variable));
    }

    public QState(Path<? extends State> path) {
        super(path.getType(), path.getMetadata());
    }

    public QState(PathMetadata<?> metadata) {
        super(State.class, metadata);
    }

}

