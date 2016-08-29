package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOperationRegion is a Querydsl query type for OperationRegion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOperationRegion extends EntityPathBase<OperationRegionEntity> {

    private static final long serialVersionUID = -1034827476L;

    public static final QOperationRegion operationRegion = new QOperationRegion("operationRegion");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QOperationRegion(String variable) {
        super(OperationRegionEntity.class, forVariable(variable));
    }

    public QOperationRegion(Path<? extends OperationRegionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperationRegion(PathMetadata<?> metadata) {
        super(OperationRegionEntity.class, metadata);
    }

}

