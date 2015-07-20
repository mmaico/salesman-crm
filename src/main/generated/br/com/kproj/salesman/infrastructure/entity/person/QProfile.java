package br.com.kproj.salesman.infrastructure.entity.person;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = -1459449921L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfile profile = new QProfile("profile");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QProfile clientCompany;

    public final QProfile clientIndividual;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final QProfile providerCompany;

    public final QProfile providerIndividual;

    public QProfile(String variable) {
        this(Profile.class, forVariable(variable), INITS);
    }

    public QProfile(Path<? extends Profile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProfile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProfile(PathMetadata<?> metadata, PathInits inits) {
        this(Profile.class, metadata, inits);
    }

    public QProfile(Class<? extends Profile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clientCompany = inits.isInitialized("clientCompany") ? new QProfile(forProperty("clientCompany"), inits.get("clientCompany")) : null;
        this.clientIndividual = inits.isInitialized("clientIndividual") ? new QProfile(forProperty("clientIndividual"), inits.get("clientIndividual")) : null;
        this.providerCompany = inits.isInitialized("providerCompany") ? new QProfile(forProperty("providerCompany"), inits.get("providerCompany")) : null;
        this.providerIndividual = inits.isInitialized("providerIndividual") ? new QProfile(forProperty("providerIndividual"), inits.get("providerIndividual")) : null;
    }

}

