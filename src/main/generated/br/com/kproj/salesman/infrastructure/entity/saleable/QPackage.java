package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPackage is a Querydsl query type for Package
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPackage extends EntityPathBase<Package> {

    private static final long serialVersionUID = 1039218024L;

    public static final QPackage package$ = new QPackage("package$");

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

    //inherited
    public final NumberPath<java.math.BigDecimal> priceCost = _super.priceCost;

    public final ListPath<SaleableUnit, QSaleableUnit> saleableUnits = this.<SaleableUnit, QSaleableUnit>createList("saleableUnits", SaleableUnit.class, QSaleableUnit.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<SaleableType> type = _super.type;

    public QPackage(String variable) {
        super(Package.class, forVariable(variable));
    }

    public QPackage(Path<? extends Package> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPackage(PathMetadata<?> metadata) {
        super(Package.class, metadata);
    }

}

