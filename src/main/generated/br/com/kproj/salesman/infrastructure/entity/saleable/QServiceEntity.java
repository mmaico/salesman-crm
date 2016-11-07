package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServiceEntity is a Querydsl query type for ServiceEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QServiceEntity extends EntityPathBase<ServiceEntity> {

    private static final long serialVersionUID = 243079834L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QServiceEntity serviceEntity = new QServiceEntity("serviceEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSaleableUnitEntity saleable;

    public QServiceEntity(String variable) {
        this(ServiceEntity.class, forVariable(variable), INITS);
    }

    public QServiceEntity(Path<? extends ServiceEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QServiceEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QServiceEntity(PathMetadata metadata, PathInits inits) {
        this(ServiceEntity.class, metadata, inits);
    }

    public QServiceEntity(Class<? extends ServiceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.saleable = inits.isInitialized("saleable") ? new QSaleableUnitEntity(forProperty("saleable")) : null;
    }

}

