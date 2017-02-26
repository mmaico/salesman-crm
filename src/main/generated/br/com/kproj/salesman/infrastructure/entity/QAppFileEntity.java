package br.com.kproj.salesman.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppFileEntity is a Querydsl query type for AppFileEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppFileEntity extends EntityPathBase<AppFileEntity> {

    private static final long serialVersionUID = 740371377L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppFileEntity appFileEntity = new QAppFileEntity("appFileEntity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final StringPath cdnUrl = createString("cdnUrl");

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lasMotification = createDateTime("lasMotification", java.util.Date.class);

    public final StringPath mimeType = createString("mimeType");

    public final StringPath originalName = createString("originalName");

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final QStorageEntity storageEntity;

    public final StringPath systemname = createString("systemname");

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QAppFileEntity(String variable) {
        this(AppFileEntity.class, forVariable(variable), INITS);
    }

    public QAppFileEntity(Path<? extends AppFileEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppFileEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppFileEntity(PathMetadata metadata, PathInits inits) {
        this(AppFileEntity.class, metadata, inits);
    }

    public QAppFileEntity(Class<? extends AppFileEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.storageEntity = inits.isInitialized("storageEntity") ? new QStorageEntity(forProperty("storageEntity")) : null;
    }

}

