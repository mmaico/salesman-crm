package br.com.kproj.salesman.infrastructure.entity.sale;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSalesOrder is a Querydsl query type for SalesOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrder extends EntityPathBase<SalesOrder> {

    private static final long serialVersionUID = -1527365670L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrder salesOrder = new QSalesOrder("salesOrder");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson client;

    public final DateTimePath<java.util.Date> deliveryForecast = createDateTime("deliveryForecast", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegion operationRegion;

    public final ListPath<SalesOrderPaymentItem, QSalesOrderPaymentItem> paymentItems = this.<SalesOrderPaymentItem, QSalesOrderPaymentItem>createList("paymentItems", SalesOrderPaymentItem.class, QSalesOrderPaymentItem.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposal proposal;

    public final ListPath<SalesOrderItem, QSalesOrderItem> saleableItems = this.<SalesOrderItem, QSalesOrderItem>createList("saleableItems", SalesOrderItem.class, QSalesOrderItem.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.QUser vendor;

    public QSalesOrder(String variable) {
        this(SalesOrder.class, forVariable(variable), INITS);
    }

    public QSalesOrder(Path<? extends SalesOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrder(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrder.class, metadata, inits);
    }

    public QSalesOrder(Class<? extends SalesOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("client"), inits.get("client")) : null;
        this.operationRegion = inits.isInitialized("operationRegion") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegion(forProperty("operationRegion")) : null;
        this.proposal = inits.isInitialized("proposal") ? new br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposal(forProperty("proposal"), inits.get("proposal")) : null;
        this.vendor = inits.isInitialized("vendor") ? new br.com.kproj.salesman.infrastructure.entity.QUser(forProperty("vendor"), inits.get("vendor")) : null;
    }

}

