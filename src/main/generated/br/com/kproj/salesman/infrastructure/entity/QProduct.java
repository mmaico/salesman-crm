package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1797724128L;

    public static final QProduct product = new QProduct("product");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.SaleTemperature> temperature = createEnum("temperature", br.com.kproj.salesman.infrastructure.entity.enums.SaleTemperature.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata<?> metadata) {
        super(Product.class, metadata);
    }

}

