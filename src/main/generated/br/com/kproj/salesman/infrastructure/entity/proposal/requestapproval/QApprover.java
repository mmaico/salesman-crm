package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QApprover is a Querydsl query type for Approver
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApprover extends EntityPathBase<Approver> {

    private static final long serialVersionUID = -986538290L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApprover approver1 = new QApprover("approver1");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity approver;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRequestApprovalEntity requestApprovalEntity;

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus.class);

    public QApprover(String variable) {
        this(Approver.class, forVariable(variable), INITS);
    }

    public QApprover(Path<? extends Approver> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApprover(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApprover(PathMetadata<?> metadata, PathInits inits) {
        this(Approver.class, metadata, inits);
    }

    public QApprover(Class<? extends Approver> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approver = inits.isInitialized("approver") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("approver"), inits.get("approver")) : null;
        this.requestApprovalEntity = inits.isInitialized("requestApprovalEntity") ? new QRequestApprovalEntity(forProperty("requestApprovalEntity"), inits.get("requestApprovalEntity")) : null;
    }

}

