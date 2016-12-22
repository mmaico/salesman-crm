package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivityCompaignEntity is a Querydsl query type for CalendarActivityCompaignEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivityCompaignEntity extends EntityPathBase<CalendarActivityCompaignEntity> {

    private static final long serialVersionUID = 179285255L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivityCompaignEntity calendarActivityCompaignEntity = new QCalendarActivityCompaignEntity("calendarActivityCompaignEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.campaigns.QCampaignEntity campaign;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCalendarActivityCompaignEntity(String variable) {
        this(CalendarActivityCompaignEntity.class, forVariable(variable), INITS);
    }

    public QCalendarActivityCompaignEntity(Path<? extends CalendarActivityCompaignEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivityCompaignEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivityCompaignEntity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivityCompaignEntity.class, metadata, inits);
    }

    public QCalendarActivityCompaignEntity(Class<? extends CalendarActivityCompaignEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.campaign = inits.isInitialized("campaign") ? new br.com.kproj.salesman.infrastructure.entity.campaigns.QCampaignEntity(forProperty("campaign"), inits.get("campaign")) : null;
    }

}

