package br.com.kproj.salesman.infrastructure.entity.sale;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QSalesOrder is a Querydsl query type for SalesOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrder extends EntityPathBase<SalesOrderEntity> {

    private static final long serialVersionUID = -1527365670L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrder salesOrder = new QSalesOrder("salesOrder");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson client;

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> deliveryForecast = createDateTime("deliveryForecast", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity operationRegionEntity;

    public final ListPath<SalesOrderPaymentItem, QSalesOrderPaymentItem> paymentItems = this.<SalesOrderPaymentItem, QSalesOrderPaymentItem>createList("paymentItems", SalesOrderPaymentItem.class, QSalesOrderPaymentItem.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposalEntity proposal;

    public final ListPath<SalesOrderItem, QSalesOrderItem> salesOrderItems = this.<SalesOrderItem, QSalesOrderItem>createList("salesOrderItems", SalesOrderItem.class, QSalesOrderItem.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity seller;

    public final BooleanPath taskGenerated = createBoolean("taskGenerated");

    public QSalesOrder(String variable) {
        this(SalesOrderEntity.class, forVariable(variable), INITS);
    }

    public QSalesOrder(Path<? extends SalesOrderEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrder(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrderEntity.class, metadata, inits);
    }

    public QSalesOrder(Class<? extends SalesOrderEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("client"), inits.get("client")) : null;
        this.operationRegionEntity = inits.isInitialized("operationRegionEntity") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity(forProperty("operationRegionEntity")) : null;
        this.proposal = inits.isInitialized("proposal") ? new br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposalEntity(forProperty("proposal"), inits.get("proposal")) : null;
        this.seller = inits.isInitialized("seller") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("seller"), inits.get("seller")) : null;
    }

}

