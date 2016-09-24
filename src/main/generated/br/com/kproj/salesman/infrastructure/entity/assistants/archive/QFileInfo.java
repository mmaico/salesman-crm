package br.com.kproj.salesman.infrastructure.entity.assistants.archive;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QFileInfo is a Querydsl query type for FileInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFileInfo extends EntityPathBase<FileInfoEntity> {

    private static final long serialVersionUID = 856068128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileInfo fileInfo = new QFileInfo("fileInfo");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final StringPath description = createString("description");

    public final br.com.kproj.salesman.infrastructure.entity.QAppFile file;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity owner;

    public final ListPath<SharedWithEntity, QSharedWith> sharedWith = this.<SharedWithEntity, QSharedWith>createList("sharedWith", SharedWithEntity.class, QSharedWith.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QFileInfo(String variable) {
        this(FileInfoEntity.class, forVariable(variable), INITS);
    }

    public QFileInfo(Path<? extends FileInfoEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileInfo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileInfo(PathMetadata<?> metadata, PathInits inits) {
        this(FileInfoEntity.class, metadata, inits);
    }

    public QFileInfo(Class<? extends FileInfoEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new br.com.kproj.salesman.infrastructure.entity.QAppFile(forProperty("file")) : null;
        this.owner = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

