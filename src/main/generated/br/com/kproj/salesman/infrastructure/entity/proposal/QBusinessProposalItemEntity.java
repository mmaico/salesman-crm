package br.com.kproj.salesman.infrastructure.entity.proposal;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessProposalItemEntity is a Querydsl query type for BusinessProposalItemEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusinessProposalItemEntity extends EntityPathBase<BusinessProposalItemEntity> {

    private static final long serialVersionUID = -2031449739L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessProposalItemEntity businessProposalItemEntity = new QBusinessProposalItemEntity("businessProposalItemEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QBusinessProposalEntity businessProposal;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> originalPrice = createNumber("originalPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QBusinessProposalItemEntity(String variable) {
        this(BusinessProposalItemEntity.class, forVariable(variable), INITS);
    }

    public QBusinessProposalItemEntity(Path<? extends BusinessProposalItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessProposalItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessProposalItemEntity(PathMetadata metadata, PathInits inits) {
        this(BusinessProposalItemEntity.class, metadata, inits);
    }

    public QBusinessProposalItemEntity(Class<? extends BusinessProposalItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessProposal = inits.isInitialized("businessProposal") ? new QBusinessProposalEntity(forProperty("businessProposal"), inits.get("businessProposal")) : null;
    }

}

