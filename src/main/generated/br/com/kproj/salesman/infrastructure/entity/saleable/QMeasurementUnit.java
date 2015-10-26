package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMeasurementUnit is a Querydsl query type for MeasurementUnit
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMeasurementUnit extends EntityPathBase<MeasurementUnit> {

    private static final long serialVersionUID = -81745278L;

    public static final QMeasurementUnit measurementUnit = new QMeasurementUnit("measurementUnit");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QMeasurementUnit(String variable) {
        super(MeasurementUnit.class, forVariable(variable));
    }

    public QMeasurementUnit(Path<? extends MeasurementUnit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeasurementUnit(PathMetadata<?> metadata) {
        super(MeasurementUnit.class, metadata);
    }

}

