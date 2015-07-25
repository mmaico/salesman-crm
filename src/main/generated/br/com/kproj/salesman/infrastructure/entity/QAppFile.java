package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAppFile is a Querydsl query type for AppFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAppFile extends EntityPathBase<AppFile> {

    private static final long serialVersionUID = 1312831022L;

    public static final QAppFile appFile = new QAppFile("appFile");

    public final QIdentifiable _super = new QIdentifiable(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mimeType = createString("mimeType");

    public final StringPath originalName = createString("originalName");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public QAppFile(String variable) {
        super(AppFile.class, forVariable(variable));
    }

    public QAppFile(Path<? extends AppFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppFile(PathMetadata<?> metadata) {
        super(AppFile.class, metadata);
    }

}

