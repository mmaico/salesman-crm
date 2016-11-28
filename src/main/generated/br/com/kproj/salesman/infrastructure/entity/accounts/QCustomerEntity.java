package br.com.kproj.salesman.infrastructure.entity.accounts;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerEntity is a Querydsl query type for CustomerEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerEntity extends EntityPathBase<CustomerEntity> {

    private static final long serialVersionUID = -2014772838L;

    public static final QCustomerEntity customerEntity = new QCustomerEntity("customerEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<AddressEntity, QAddressEntity> addresses = this.<AddressEntity, QAddressEntity>createList("addresses", AddressEntity.class, QAddressEntity.class, PathInits.DIRECT2);

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.ContactEntity, br.com.kproj.salesman.infrastructure.entity.QContactEntity> contacts = this.<br.com.kproj.salesman.infrastructure.entity.ContactEntity, br.com.kproj.salesman.infrastructure.entity.QContactEntity>createList("contacts", br.com.kproj.salesman.infrastructure.entity.ContactEntity.class, br.com.kproj.salesman.infrastructure.entity.QContactEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath site = createString("site");

    public QCustomerEntity(String variable) {
        super(CustomerEntity.class, forVariable(variable));
    }

    public QCustomerEntity(Path<? extends CustomerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomerEntity(PathMetadata metadata) {
        super(CustomerEntity.class, metadata);
    }

}

