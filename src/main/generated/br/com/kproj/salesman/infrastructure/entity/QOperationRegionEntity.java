package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOperationRegionEntity is a Querydsl query type for OperationRegionEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOperationRegionEntity extends EntityPathBase<OperationRegionEntity> {

    private static final long serialVersionUID = 1504446511L;

    public static final QOperationRegionEntity operationRegionEntity = new QOperationRegionEntity("operationRegionEntity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QOperationRegionEntity(String variable) {
        super(OperationRegionEntity.class, forVariable(variable));
    }

    public QOperationRegionEntity(Path<? extends OperationRegionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperationRegionEntity(PathMetadata<?> metadata) {
        super(OperationRegionEntity.class, metadata);
    }

}
