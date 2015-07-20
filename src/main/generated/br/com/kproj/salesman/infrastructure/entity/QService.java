package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QService is a Querydsl query type for Service
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QService extends EntityPathBase<Service> {

    private static final long serialVersionUID = -203615834L;

    public static final QService service = new QService("service");

    public final QProduct _super = new QProduct(this);

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.SaleTemperature> temperature = _super.temperature;

    public QService(String variable) {
        super(Service.class, forVariable(variable));
    }

    public QService(Path<? extends Service> path) {
        super(path.getType(), path.getMetadata());
    }

    public QService(PathMetadata<?> metadata) {
        super(Service.class, metadata);
    }

}

