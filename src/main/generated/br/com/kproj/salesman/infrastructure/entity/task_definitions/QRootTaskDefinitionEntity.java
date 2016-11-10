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

    public final QTaskDefinitionEntity _super = new QTaskDefinitionEntity(this);

    //inherited
    public final ListPath<ChecklistDefinitionEntity, QChecklistDefinitionEntity> checklist = _super.checklist;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Integer> quantityDaysTofinishAfertSignedContract = _super.quantityDaysTofinishAfertSignedContract;

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity region;

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity saleable;

    //inherited
    public final StringPath title = _super.title;

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
        this.region = inits.isInitialized("region") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity(forProperty("region")) : null;
        this.saleable = inits.isInitialized("saleable") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity(forProperty("saleable")) : null;
    }

}

