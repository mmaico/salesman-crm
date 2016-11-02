package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSaleableUnitEntity is a Querydsl query type for SaleableUnitEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSaleableUnitEntity extends EntityPathBase<SaleableUnitEntity> {

    private static final long serialVersionUID = -2037707482L;

    public static final QSaleableUnitEntity saleableUnitEntity = new QSaleableUnitEntity("saleableUnitEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> priceCost = createNumber("priceCost", java.math.BigDecimal.class);

    public final EnumPath<SaleableTypeEntity> type = createEnum("type", SaleableTypeEntity.class);

    public QSaleableUnitEntity(String variable) {
        super(SaleableUnitEntity.class, forVariable(variable));
    }

    public QSaleableUnitEntity(Path<? extends SaleableUnitEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSaleableUnitEntity(PathMetadata metadata) {
        super(SaleableUnitEntity.class, metadata);
    }

}

