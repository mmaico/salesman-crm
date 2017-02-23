package br.com.kproj.salesman.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppFileEntity is a Querydsl query type for AppFileEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppFileEntity extends EntityPathBase<AppFileEntity> {

    private static final long serialVersionUID = 740371377L;

    public static final QAppFileEntity appFileEntity = new QAppFileEntity("appFileEntity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final StringPath cdnUrl = createString("cdnUrl");

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lasMotification = createDateTime("lasMotification", java.util.Date.class);

    public final StringPath mimeType = createString("mimeType");

    public final StringPath originalName = createString("originalName");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final StringPath systemname = createString("systemname");

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QAppFileEntity(String variable) {
        super(AppFileEntity.class, forVariable(variable));
    }

    public QAppFileEntity(Path<? extends AppFileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppFileEntity(PathMetadata metadata) {
        super(AppFileEntity.class, metadata);
    }

}

