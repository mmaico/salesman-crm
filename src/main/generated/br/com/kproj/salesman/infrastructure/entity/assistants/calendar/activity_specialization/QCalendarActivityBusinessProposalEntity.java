package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivityBusinessProposalEntity is a Querydsl query type for CalendarActivityBusinessProposalEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivityBusinessProposalEntity extends EntityPathBase<CalendarActivityBusinessProposalEntity> {

    private static final long serialVersionUID = 493682747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivityBusinessProposalEntity calendarActivityBusinessProposalEntity = new QCalendarActivityBusinessProposalEntity("calendarActivityBusinessProposalEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposalEntity proposal;

    public QCalendarActivityBusinessProposalEntity(String variable) {
        this(CalendarActivityBusinessProposalEntity.class, forVariable(variable), INITS);
    }

    public QCalendarActivityBusinessProposalEntity(Path<? extends CalendarActivityBusinessProposalEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivityBusinessProposalEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivityBusinessProposalEntity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivityBusinessProposalEntity.class, metadata, inits);
    }

    public QCalendarActivityBusinessProposalEntity(Class<? extends CalendarActivityBusinessProposalEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.proposal = inits.isInitialized("proposal") ? new br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposalEntity(forProperty("proposal"), inits.get("proposal")) : null;
    }

}

