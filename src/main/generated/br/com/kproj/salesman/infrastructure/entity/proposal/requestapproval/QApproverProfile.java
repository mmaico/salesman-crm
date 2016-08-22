package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QApproverProfile is a Querydsl query type for ApproverProfile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApproverProfile extends EntityPathBase<ApproverProfile> {

    private static final long serialVersionUID = 1077468923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApproverProfile approverProfile = new QApproverProfile("approverProfile");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity approver;

    public final BooleanPath available = createBoolean("available");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QApproverProfile(String variable) {
        this(ApproverProfile.class, forVariable(variable), INITS);
    }

    public QApproverProfile(Path<? extends ApproverProfile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApproverProfile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApproverProfile(PathMetadata<?> metadata, PathInits inits) {
        this(ApproverProfile.class, metadata, inits);
    }

    public QApproverProfile(Class<? extends ApproverProfile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approver = inits.isInitialized("approver") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("approver"), inits.get("approver")) : null;
    }

}

