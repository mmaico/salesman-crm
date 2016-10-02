package br.com.kproj.salesman.infrastructure.entity.assistants.archive;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFileInfoEntity is a Querydsl query type for FileInfoEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFileInfoEntity extends EntityPathBase<FileInfoEntity> {

    private static final long serialVersionUID = -257636317L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileInfoEntity fileInfoEntity = new QFileInfoEntity("fileInfoEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final StringPath description = createString("description");

    public final br.com.kproj.salesman.infrastructure.entity.QAppFile file;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity owner;

    public final ListPath<SharedWithEntity, QSharedWithEntity> sharedWithEntity = this.<SharedWithEntity, QSharedWithEntity>createList("sharedWithEntity", SharedWithEntity.class, QSharedWithEntity.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QFileInfoEntity(String variable) {
        this(FileInfoEntity.class, forVariable(variable), INITS);
    }

    public QFileInfoEntity(Path<? extends FileInfoEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileInfoEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileInfoEntity(PathMetadata<?> metadata, PathInits inits) {
        this(FileInfoEntity.class, metadata, inits);
    }

    public QFileInfoEntity(Class<? extends FileInfoEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new br.com.kproj.salesman.infrastructure.entity.QAppFile(forProperty("file")) : null;
        this.owner = inits.isInitialized("owner") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("owner"), inits.get("owner")) : null;
    }

}

