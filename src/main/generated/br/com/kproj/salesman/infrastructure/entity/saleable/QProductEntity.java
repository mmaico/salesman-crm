package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductEntity is a Querydsl query type for ProductEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductEntity extends EntityPathBase<ProductEntity> {

    private static final long serialVersionUID = -942404268L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductEntity productEntity = new QProductEntity("productEntity");

    public final QSaleableUnitEntity _super = new QSaleableUnitEntity(this);

    //inherited
    public final BooleanPath active = _super.active;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMeasurementUnitEntity measurementUnit;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<java.math.BigDecimal> price = _super.price;

    //inherited
    public final NumberPath<java.math.BigDecimal> priceCost = _super.priceCost;

    //inherited
    public final EnumPath<SaleableTypeEntity> type = _super.type;

    public QProductEntity(String variable) {
        this(ProductEntity.class, forVariable(variable), INITS);
    }

    public QProductEntity(Path<? extends ProductEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductEntity(PathMetadata metadata, PathInits inits) {
        this(ProductEntity.class, metadata, inits);
    }

    public QProductEntity(Class<? extends ProductEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.measurementUnit = inits.isInitialized("measurementUnit") ? new QMeasurementUnitEntity(forProperty("measurementUnit")) : null;
    }

}

