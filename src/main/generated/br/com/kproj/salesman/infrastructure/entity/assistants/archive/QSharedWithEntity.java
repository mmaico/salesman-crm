package br.com.kproj.salesman.infrastructure.entity.assistants.archive;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSharedWithEntity is a Querydsl query type for SharedWithEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSharedWithEntity extends EntityPathBase<SharedWithEntity> {

    private static final long serialVersionUID = -1629811612L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSharedWithEntity sharedWithEntity = new QSharedWithEntity("sharedWithEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QFileInfoEntity fileInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.SharedTypeEntity> type = createEnum("type", br.com.kproj.salesman.infrastructure.entity.enums.SharedTypeEntity.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QSharedWithEntity(String variable) {
        this(SharedWithEntity.class, forVariable(variable), INITS);
    }

    public QSharedWithEntity(Path<? extends SharedWithEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSharedWithEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSharedWithEntity(PathMetadata<?> metadata, PathInits inits) {
        this(SharedWithEntity.class, metadata, inits);
    }

    public QSharedWithEntity(Class<? extends SharedWithEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileInfo = inits.isInitialized("fileInfo") ? new QFileInfoEntity(forProperty("fileInfo"), inits.get("fileInfo")) : null;
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

