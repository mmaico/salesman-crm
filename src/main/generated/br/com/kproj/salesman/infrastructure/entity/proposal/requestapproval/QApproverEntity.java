package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApproverEntity is a Querydsl query type for ApproverEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApproverEntity extends EntityPathBase<ApproverEntity> {

    private static final long serialVersionUID = -1114989487L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApproverEntity approverEntity = new QApproverEntity("approverEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity approver;

    public final BooleanPath available = createBoolean("available");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QApproverEntity(String variable) {
        this(ApproverEntity.class, forVariable(variable), INITS);
    }

    public QApproverEntity(Path<? extends ApproverEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApproverEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApproverEntity(PathMetadata metadata, PathInits inits) {
        this(ApproverEntity.class, metadata, inits);
    }

    public QApproverEntity(Class<? extends ApproverEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approver = inits.isInitialized("approver") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("approver"), inits.get("approver")) : null;
    }

}

