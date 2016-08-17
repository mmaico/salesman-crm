package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMeasurementUnitEntity is a Querydsl query type for MeasurementUnitEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMeasurementUnitEntity extends EntityPathBase<MeasurementUnitEntity> {

    private static final long serialVersionUID = 1600524549L;

    public static final QMeasurementUnitEntity measurementUnitEntity = new QMeasurementUnitEntity("measurementUnitEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QMeasurementUnitEntity(String variable) {
        super(MeasurementUnitEntity.class, forVariable(variable));
    }

    public QMeasurementUnitEntity(Path<? extends MeasurementUnitEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeasurementUnitEntity(PathMetadata<?> metadata) {
        super(MeasurementUnitEntity.class, metadata);
    }

}

