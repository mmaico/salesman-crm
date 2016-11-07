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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalePackageEntity salePackageEntity = new QSalePackageEntity("salePackageEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath priceByProducts = createBoolean("priceByProducts");

    public final ListPath<SaleableRelationEntity, QSaleableRelationEntity> relations = this.<SaleableRelationEntity, QSaleableRelationEntity>createList("relations", SaleableRelationEntity.class, QSaleableRelationEntity.class, PathInits.DIRECT2);

    public final QSaleableUnitEntity saleable;

    public QSalePackageEntity(String variable) {
        this(SalePackageEntity.class, forVariable(variable), INITS);
    }

    public QSalePackageEntity(Path<? extends SalePackageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSalePackageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSalePackageEntity(PathMetadata metadata, PathInits inits) {
        this(SalePackageEntity.class, metadata, inits);
    }

    public QSalePackageEntity(Class<? extends SalePackageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.saleable = inits.isInitialized("saleable") ? new QSaleableUnitEntity(forProperty("saleable")) : null;
    }

}

