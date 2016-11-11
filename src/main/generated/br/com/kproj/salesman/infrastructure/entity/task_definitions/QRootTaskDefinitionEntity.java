package br.com.kproj.salesman.infrastructure.entity.task_definitions;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRootTaskDefinitionEntity is a Querydsl query type for RootTaskDefinitionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRootTaskDefinitionEntity extends EntityPathBase<RootTaskDefinitionEntity> {

    private static final long serialVersionUID = 89253078L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRootTaskDefinitionEntity rootTaskDefinitionEntity = new QRootTaskDefinitionEntity("rootTaskDefinitionEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTaskDefinitionEntity taskDefinition;

    public QRootTaskDefinitionEntity(String variable) {
        this(RootTaskDefinitionEntity.class, forVariable(variable), INITS);
    }

    public QRootTaskDefinitionEntity(Path<? extends RootTaskDefinitionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRootTaskDefinitionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRootTaskDefinitionEntity(PathMetadata metadata, PathInits inits) {
        this(RootTaskDefinitionEntity.class, metadata, inits);
    }

    public QRootTaskDefinitionEntity(Class<? extends RootTaskDefinitionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskDefinition = inits.isInitialized("taskDefinition") ? new QTaskDefinitionEntity(forProperty("taskDefinition"), inits.get("taskDefinition")) : null;
    }

}

