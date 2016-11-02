package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QServiceEntity is a Querydsl query type for ServiceEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QServiceEntity extends EntityPathBase<ServiceEntity> {

    private static final long serialVersionUID = 243079834L;

    public static final QServiceEntity serviceEntity = new QServiceEntity("serviceEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QServiceEntity(String variable) {
        super(ServiceEntity.class, forVariable(variable));
    }

    public QServiceEntity(Path<? extends ServiceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServiceEntity(PathMetadata metadata) {
        super(ServiceEntity.class, metadata);
    }

}

