package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSaleableUnit is a Querydsl query type for SaleableUnit
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleableUnit extends EntityPathBase<SaleableUnit> {

    private static final long serialVersionUID = 809079651L;

    public static final QSaleableUnit saleableUnit = new QSaleableUnit("saleableUnit");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> priceCost = createNumber("priceCost", java.math.BigDecimal.class);

    public final EnumPath<SaleableType> type = createEnum("type", SaleableType.class);

    public QSaleableUnit(String variable) {
        super(SaleableUnit.class, forVariable(variable));
    }

    public QSaleableUnit(Path<? extends SaleableUnit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSaleableUnit(PathMetadata<?> metadata) {
        super(SaleableUnit.class, metadata);
    }

}

