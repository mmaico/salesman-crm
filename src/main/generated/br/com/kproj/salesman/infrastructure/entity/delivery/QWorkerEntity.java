package br.com.kproj.salesman.infrastructure.entity.delivery;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkerEntity is a Querydsl query type for WorkerEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkerEntity extends EntityPathBase<WorkerEntity> {

    private static final long serialVersionUID = 1236467244L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkerEntity workerEntity = new QWorkerEntity("workerEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QDeliveryEntity delivery;

    public final DateTimePath<java.util.Date> deliveryForecast = createDateTime("deliveryForecast", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QWorkerEntity(String variable) {
        this(WorkerEntity.class, forVariable(variable), INITS);
    }

    public QWorkerEntity(Path<? extends WorkerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkerEntity(PathMetadata metadata, PathInits inits) {
        this(WorkerEntity.class, metadata, inits);
    }

    public QWorkerEntity(Class<? extends WorkerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDeliveryEntity(forProperty("delivery"), inits.get("delivery")) : null;
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

