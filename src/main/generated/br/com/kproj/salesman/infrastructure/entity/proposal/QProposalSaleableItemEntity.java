package br.com.kproj.salesman.infrastructure.entity.proposal;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProposalSaleableItemEntity is a Querydsl query type for ProposalSaleableItemEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProposalSaleableItemEntity extends EntityPathBase<ProposalSaleableItemEntity> {

    private static final long serialVersionUID = -62780266L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProposalSaleableItemEntity proposalSaleableItemEntity = new QProposalSaleableItemEntity("proposalSaleableItemEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QBusinessProposalItemEntity negotiated;

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity saleable;

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSalePackageEntity usedPackage;

    public QProposalSaleableItemEntity(String variable) {
        this(ProposalSaleableItemEntity.class, forVariable(variable), INITS);
    }

    public QProposalSaleableItemEntity(Path<? extends ProposalSaleableItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProposalSaleableItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProposalSaleableItemEntity(PathMetadata metadata, PathInits inits) {
        this(ProposalSaleableItemEntity.class, metadata, inits);
    }

    public QProposalSaleableItemEntity(Class<? extends ProposalSaleableItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.negotiated = inits.isInitialized("negotiated") ? new QBusinessProposalItemEntity(forProperty("negotiated"), inits.get("negotiated")) : null;
        this.saleable = inits.isInitialized("saleable") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity(forProperty("saleable")) : null;
        this.usedPackage = inits.isInitialized("usedPackage") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSalePackageEntity(forProperty("usedPackage"), inits.get("usedPackage")) : null;
    }

}

