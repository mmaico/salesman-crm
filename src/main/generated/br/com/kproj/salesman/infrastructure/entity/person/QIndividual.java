package br.com.kproj.salesman.infrastructure.entity.person;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIndividual is a Querydsl query type for Individual
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIndividual extends EntityPathBase<Individual> {

    private static final long serialVersionUID = 537779395L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIndividual individual = new QIndividual("individual");

    public final QPerson _super;

    //inherited
    public final BooleanPath active;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.Contact, br.com.kproj.salesman.infrastructure.entity.QContact> contacts;

    public final StringPath cpf = createString("cpf");

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final StringPath name;

    // inherited
    public final QProfile profile;

    public QIndividual(String variable) {
        this(Individual.class, forVariable(variable), INITS);
    }

    public QIndividual(Path<? extends Individual> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIndividual(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIndividual(PathMetadata<?> metadata, PathInits inits) {
        this(Individual.class, metadata, inits);
    }

    public QIndividual(Class<? extends Individual> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QPerson(type, metadata, inits);
        this.active = _super.active;
        this.contacts = _super.contacts;
        this.id = _super.id;
        this.name = _super.name;
        this.profile = _super.profile;
    }

}
