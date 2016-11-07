package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaleableRelationEntity is a Querydsl query type for SaleableRelationEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSaleableRelationEntity extends EntityPathBase<SaleableRelationEntity> {

    private static final long serialVersionUID = -1461613538L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleableRelationEntity saleableRelationEntity = new QSaleableRelationEntity("saleableRelationEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSaleableUnitEntity saleable;

    public final QSalePackageEntity salePackage;

    public QSaleableRelationEntity(String variable) {
        this(SaleableRelationEntity.class, forVariable(variable), INITS);
    }

    public QSaleableRelationEntity(Path<? extends SaleableRelationEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaleableRelationEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaleableRelationEntity(PathMetadata metadata, PathInits inits) {
        this(SaleableRelationEntity.class, metadata, inits);
    }

    public QSaleableRelationEntity(Class<? extends SaleableRelationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.saleable = inits.isInitialized("saleable") ? new QSaleableUnitEntity(forProperty("saleable")) : null;
        this.salePackage = inits.isInitialized("salePackage") ? new QSalePackageEntity(forProperty("salePackage"), inits.get("salePackage")) : null;
    }

}

