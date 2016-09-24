package br.com.kproj.salesman.infrastructure.entity.accounts;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QAccountEntity is a Querydsl query type for AccountEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAccountEntity extends EntityPathBase<AccountEntity> {

    private static final long serialVersionUID = -142128873L;

    public static final QAccountEntity accountEntity = new QAccountEntity("accountEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<AddressEntity, QAddressEntity> addresses = this.<AddressEntity, QAddressEntity>createList("addresses", AddressEntity.class, QAddressEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> employers = createNumber("employers", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath site = createString("site");

    public QAccountEntity(String variable) {
        super(AccountEntity.class, forVariable(variable));
    }

    public QAccountEntity(Path<? extends AccountEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountEntity(PathMetadata<?> metadata) {
        super(AccountEntity.class, metadata);
    }

}

