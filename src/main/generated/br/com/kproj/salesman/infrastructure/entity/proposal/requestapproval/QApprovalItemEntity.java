package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApprovalItemEntity is a Querydsl query type for ApprovalItemEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApprovalItemEntity extends EntityPathBase<ApprovalItemEntity> {

    private static final long serialVersionUID = -1631683518L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApprovalItemEntity approvalItemEntity = new QApprovalItemEntity("approvalItemEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QApproverEntity approver;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRequestApprovalEntity requestApproval;

    public final EnumPath<ApprovalItemEntity.StatusEntity> status = createEnum("status", ApprovalItemEntity.StatusEntity.class);

    public QApprovalItemEntity(String variable) {
        this(ApprovalItemEntity.class, forVariable(variable), INITS);
    }

    public QApprovalItemEntity(Path<? extends ApprovalItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApprovalItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApprovalItemEntity(PathMetadata metadata, PathInits inits) {
        this(ApprovalItemEntity.class, metadata, inits);
    }

    public QApprovalItemEntity(Class<? extends ApprovalItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approver = inits.isInitialized("approver") ? new QApproverEntity(forProperty("approver"), inits.get("approver")) : null;
        this.requestApproval = inits.isInitialized("requestApproval") ? new QRequestApprovalEntity(forProperty("requestApproval"), inits.get("requestApproval")) : null;
    }

}

