package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRequestApproval is a Querydsl query type for RequestApproval
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRequestApproval extends EntityPathBase<RequestApproval> {

    private static final long serialVersionUID = 1116019145L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequestApproval requestApproval = new QRequestApproval("requestApproval");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<Approver, QApprover> approvers = this.<Approver, QApprover>createList("approvers", Approver.class, QApprover.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposal proposal;

    public final EnumPath<RequestApproval.RequestApprovalStatus> status = createEnum("status", RequestApproval.RequestApprovalStatus.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUser userRequester;

    public QRequestApproval(String variable) {
        this(RequestApproval.class, forVariable(variable), INITS);
    }

    public QRequestApproval(Path<? extends RequestApproval> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRequestApproval(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRequestApproval(PathMetadata<?> metadata, PathInits inits) {
        this(RequestApproval.class, metadata, inits);
    }

    public QRequestApproval(Class<? extends RequestApproval> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.proposal = inits.isInitialized("proposal") ? new br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposal(forProperty("proposal"), inits.get("proposal")) : null;
        this.userRequester = inits.isInitialized("userRequester") ? new br.com.kproj.salesman.infrastructure.entity.QUser(forProperty("userRequester"), inits.get("userRequester")) : null;
    }

}
