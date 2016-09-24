package br.com.kproj.salesman.infrastructure.entity.saleable;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QServiceEntity is a Querydsl query type for ServiceEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QServiceEntity extends EntityPathBase<ServiceEntity> {

    private static final long serialVersionUID = 243079834L;

    public static final QServiceEntity serviceEntity = new QServiceEntity("serviceEntity");

    public final QSaleableUnitEntity _super = new QSaleableUnitEntity(this);

    //inherited
    public final BooleanPath active = _super.active;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<java.math.BigDecimal> price = _super.price;

    //inherited
    public final NumberPath<java.math.BigDecimal> priceCost = _super.priceCost;

    //inherited
    public final EnumPath<SaleableTypeEntity> type = _super.type;

    public QServiceEntity(String variable) {
        super(ServiceEntity.class, forVariable(variable));
    }

    public QServiceEntity(Path<? extends ServiceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServiceEntity(PathMetadata<?> metadata) {
        super(ServiceEntity.class, metadata);
    }

}

