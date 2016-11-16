package br.com.kproj.salesman.infrastructure.entity.delivery;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryEntity is a Querydsl query type for DeliveryEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeliveryEntity extends EntityPathBase<DeliveryEntity> {

    private static final long serialVersionUID = 643266402L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryEntity deliveryEntity = new QDeliveryEntity("deliveryEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final DateTimePath<java.util.Date> deliveryForecast = createDateTime("deliveryForecast", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrderEntity salesOrder;

    public final ListPath<WorkerEntity, QWorkerEntity> workers = this.<WorkerEntity, QWorkerEntity>createList("workers", WorkerEntity.class, QWorkerEntity.class, PathInits.DIRECT2);

    public QDeliveryEntity(String variable) {
        this(DeliveryEntity.class, forVariable(variable), INITS);
    }

    public QDeliveryEntity(Path<? extends DeliveryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryEntity(PathMetadata metadata, PathInits inits) {
        this(DeliveryEntity.class, metadata, inits);
    }

    public QDeliveryEntity(Class<? extends DeliveryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.salesOrder = inits.isInitialized("salesOrder") ? new br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrderEntity(forProperty("salesOrder"), inits.get("salesOrder")) : null;
    }

}

