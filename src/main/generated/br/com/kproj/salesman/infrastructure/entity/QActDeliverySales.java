package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QActDeliverySales is a Querydsl query type for ActDeliverySales
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActDeliverySales extends EntityPathBase<ActDeliverySales> {

    private static final long serialVersionUID = -1633894859L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActDeliverySales actDeliverySales = new QActDeliverySales("actDeliverySales");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrder salesOrder;

    public final QUser user;

    public QActDeliverySales(String variable) {
        this(ActDeliverySales.class, forVariable(variable), INITS);
    }

    public QActDeliverySales(Path<? extends ActDeliverySales> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActDeliverySales(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActDeliverySales(PathMetadata<?> metadata, PathInits inits) {
        this(ActDeliverySales.class, metadata, inits);
    }

    public QActDeliverySales(Class<? extends ActDeliverySales> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.salesOrder = inits.isInitialized("salesOrder") ? new br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrder(forProperty("salesOrder"), inits.get("salesOrder")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

