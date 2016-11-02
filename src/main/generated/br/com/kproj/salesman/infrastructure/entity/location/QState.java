package br.com.kproj.salesman.infrastructure.entity.location;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QState is a Querydsl query type for State
 */
@Generated("com.querydsl.codegen.EntitySerializer")
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

    public QState(PathMetadata metadata) {
        super(State.class, metadata);
    }

}

