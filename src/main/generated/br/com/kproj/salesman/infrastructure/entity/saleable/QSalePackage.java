package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSalePackage is a Querydsl query type for SalePackage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalePackage extends EntityPathBase<SalePackageEntity> {

    private static final long serialVersionUID = -966386143L;

    public static final QSalePackage salePackage = new QSalePackage("salePackage");

    public final QSaleableUnit _super = new QSaleableUnit(this);

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

    public final ListPath<SaleableUnitEntity, QSaleableUnit> saleableUnits = this.<SaleableUnitEntity, QSaleableUnit>createList("saleableUnits", SaleableUnitEntity.class, QSaleableUnit.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<SaleableTypeEntity> type = _super.type;

    public QSalePackage(String variable) {
        super(SalePackageEntity.class, forVariable(variable));
    }

    public QSalePackage(Path<? extends SalePackageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalePackage(PathMetadata<?> metadata) {
        super(SalePackageEntity.class, metadata);
    }

}

