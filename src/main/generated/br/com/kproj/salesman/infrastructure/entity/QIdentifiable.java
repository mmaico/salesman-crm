package br.com.kproj.salesman.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIdentifiable is a Querydsl query type for Identifiable
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QIdentifiable extends EntityPathBase<Identifiable> {

    private static final long serialVersionUID = -604829179L;

    public static final QIdentifiable identifiable = new QIdentifiable("identifiable");

    public QIdentifiable(String variable) {
        super(Identifiable.class, forVariable(variable));
    }

    public QIdentifiable(Path<? extends Identifiable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdentifiable(PathMetadata metadata) {
        super(Identifiable.class, metadata);
    }

}

