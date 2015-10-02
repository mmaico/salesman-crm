package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBranch is a Querydsl query type for Branch
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBranch extends EntityPathBase<Branch> {

    private static final long serialVersionUID = -1590151023L;

    public static final QBranch branch = new QBranch("branch");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QBranch(String variable) {
        super(Branch.class, forVariable(variable));
    }

    public QBranch(Path<? extends Branch> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBranch(PathMetadata<?> metadata) {
        super(Branch.class, metadata);
    }

}

