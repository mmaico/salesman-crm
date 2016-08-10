package br.com.kproj.salesman.infrastructure.entity.saleable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QService is a Querydsl query type for Service
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QService extends EntityPathBase<ServiceEntity> {

    private static final long serialVersionUID = -464533545L;

    public static final QService service = new QService("service");

    public final QSaleableUnit _super = new QSaleableUnit(this);

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

    public QService(String variable) {
        super(ServiceEntity.class, forVariable(variable));
    }

    public QService(Path<? extends ServiceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QService(PathMetadata<?> metadata) {
        super(ServiceEntity.class, metadata);
    }

}

