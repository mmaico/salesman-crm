package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSalePackageEntity is a Querydsl query type for SalePackageEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSalePackageEntity extends EntityPathBase<SalePackageEntity> {

    private static final long serialVersionUID = 713560420L;

    public static final QSalePackageEntity salePackageEntity = new QSalePackageEntity("salePackageEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath priceByProducts = createBoolean("priceByProducts");

    public final ListPath<SaleableUnitEntity, QSaleableUnitEntity> saleableUnits = this.<SaleableUnitEntity, QSaleableUnitEntity>createList("saleableUnits", SaleableUnitEntity.class, QSaleableUnitEntity.class, PathInits.DIRECT2);

    public QSalePackageEntity(String variable) {
        super(SalePackageEntity.class, forVariable(variable));
    }

    public QSalePackageEntity(Path<? extends SalePackageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalePackageEntity(PathMetadata metadata) {
        super(SalePackageEntity.class, metadata);
    }

}

