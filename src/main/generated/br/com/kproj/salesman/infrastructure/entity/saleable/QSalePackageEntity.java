package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSalePackageEntity is a Querydsl query type for SalePackageEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalePackageEntity extends EntityPathBase<SalePackageEntity> {

    private static final long serialVersionUID = 713560420L;

    public static final QSalePackageEntity salePackageEntity = new QSalePackageEntity("salePackageEntity");

    public final QSaleableUnitEntity _super = new QSaleableUnitEntity(this);

    //inherited
    public final BooleanPath active = _super.active;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<java.math.BigDecimal> price = _super.price;

    public final BooleanPath priceByProducts = createBoolean("priceByProducts");

    //inherited
    public final NumberPath<java.math.BigDecimal> priceCost = _super.priceCost;

    public final ListPath<SaleableUnitEntity, QSaleableUnitEntity> saleableUnits = this.<SaleableUnitEntity, QSaleableUnitEntity>createList("saleableUnits", SaleableUnitEntity.class, QSaleableUnitEntity.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<SaleableTypeEntity> type = _super.type;

    public QSalePackageEntity(String variable) {
        super(SalePackageEntity.class, forVariable(variable));
    }

    public QSalePackageEntity(Path<? extends SalePackageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalePackageEntity(PathMetadata<?> metadata) {
        super(SalePackageEntity.class, metadata);
    }

}

