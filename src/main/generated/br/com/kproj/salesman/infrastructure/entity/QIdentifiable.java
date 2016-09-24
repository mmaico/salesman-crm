package br.com.kproj.salesman.infrastructure.entity;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QIdentifiable is a Querydsl query type for Identifiable
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QIdentifiable extends EntityPathBase<Identifiable> {

    private static final long serialVersionUID = -604829179L;

    public static final QIdentifiable identifiable = new QIdentifiable("identifiable");

    public QIdentifiable(String variable) {
        super(Identifiable.class, forVariable(variable));
    }

    public QIdentifiable(Path<? extends Identifiable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdentifiable(PathMetadata<?> metadata) {
        super(Identifiable.class, metadata);
    }

}

