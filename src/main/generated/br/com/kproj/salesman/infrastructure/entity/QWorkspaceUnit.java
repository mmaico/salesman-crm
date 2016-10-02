package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QWorkspaceUnit is a Querydsl query type for WorkspaceUnit
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWorkspaceUnit extends EntityPathBase<WorkspaceUnit> {

    private static final long serialVersionUID = -447383062L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkspaceUnit workspaceUnit = new QWorkspaceUnit("workspaceUnit");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrderEntity salesOrderEntity;

    public final QUserEntity user;

    public QWorkspaceUnit(String variable) {
        this(WorkspaceUnit.class, forVariable(variable), INITS);
    }

    public QWorkspaceUnit(Path<? extends WorkspaceUnit> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWorkspaceUnit(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWorkspaceUnit(PathMetadata<?> metadata, PathInits inits) {
        this(WorkspaceUnit.class, metadata, inits);
    }

    public QWorkspaceUnit(Class<? extends WorkspaceUnit> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.salesOrderEntity = inits.isInitialized("salesOrderEntity") ? new br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrderEntity(forProperty("salesOrderEntity"), inits.get("salesOrderEntity")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

