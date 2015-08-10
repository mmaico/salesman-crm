package br.com.kproj.salesman.infrastructure.entity.person;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPersonProfile is a Querydsl query type for PersonProfile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonProfile extends EntityPathBase<PersonProfile> {

    private static final long serialVersionUID = -267146710L;

    public static final QPersonProfile personProfile = new QPersonProfile("personProfile");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

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

