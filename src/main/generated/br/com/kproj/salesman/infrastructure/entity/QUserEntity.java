package br.com.kproj.salesman.infrastructure.entity;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1485208317L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.QApproverEntity approverEntity;

    public final ArrayPath<byte[], Byte> avatar = createArray("avatar", byte[].class);

    public final QBranchEntity branch;

    public final br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarEntity calendarEntity;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastname = createString("lastname");

    public final StringPath login = createString("login");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final QUserPositionEntity position;

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserEntity(PathMetadata<?> metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approverEntity = inits.isInitialized("approverEntity") ? new br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.QApproverEntity(forProperty("approverEntity"), inits.get("approverEntity")) : null;
        this.branch = inits.isInitialized("branch") ? new QBranchEntity(forProperty("branch")) : null;
        this.calendarEntity = inits.isInitialized("calendarEntity") ? new br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarEntity(forProperty("calendarEntity"), inits.get("calendarEntity")) : null;
        this.position = inits.isInitialized("position") ? new QUserPositionEntity(forProperty("position")) : null;
    }

}

