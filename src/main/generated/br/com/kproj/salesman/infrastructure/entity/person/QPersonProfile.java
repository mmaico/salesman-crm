package br.com.kproj.salesman.infrastructure.entity.person;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QPersonProfile is a Querydsl query type for PersonProfile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonProfile extends EntityPathBase<PersonProfile> {

    private static final long serialVersionUID = -267146710L;

    public static final QPersonProfile personProfile = new QPersonProfile("personProfile");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QPersonProfile(String variable) {
        super(PersonProfile.class, forVariable(variable));
    }

    public QPersonProfile(Path<? extends PersonProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonProfile(PathMetadata<?> metadata) {
        super(PersonProfile.class, metadata);
    }

}

