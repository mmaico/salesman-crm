package br.com.kproj.salesman.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppFile is a Querydsl query type for AppFileEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppFile extends EntityPathBase<AppFileEntity> {

    private static final long serialVersionUID = 1312831022L;

    public static final QAppFile appFile = new QAppFile("appFile");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lasMotification = createDateTime("lasMotification", java.util.Date.class);

    public final StringPath mimeType = createString("mimeType");

    public final StringPath originalName = createString("originalName");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QAppFile(String variable) {
        super(AppFileEntity.class, forVariable(variable));
    }

    public QAppFile(Path<? extends AppFileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppFile(PathMetadata metadata) {
        super(AppFileEntity.class, metadata);
    }

}

