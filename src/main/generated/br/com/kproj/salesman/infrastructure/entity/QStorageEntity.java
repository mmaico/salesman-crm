package br.com.kproj.salesman.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStorageEntity is a Querydsl query type for StorageEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStorageEntity extends EntityPathBase<StorageEntity> {

    private static final long serialVersionUID = -833643409L;

    public static final QStorageEntity storageEntity = new QStorageEntity("storageEntity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QStorageEntity(String variable) {
        super(StorageEntity.class, forVariable(variable));
    }

    public QStorageEntity(Path<? extends StorageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStorageEntity(PathMetadata metadata) {
        super(StorageEntity.class, metadata);
    }

}

