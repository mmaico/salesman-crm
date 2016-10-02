package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;

@Deprecated
public class TimelineSaveMessage {


    private BusinessProposalEntity businessProposalEntity;
    private UserEntity user;
    private TimelineActivity activity;

    public TimelineSaveMessage(UserEntity user, BusinessProposalEntity proposal, TimelineActivity activity) {
        this.user = user;
        this.businessProposalEntity = proposal;
        this.activity = activity;
    }

    public static TimelineSaveMessage createTimelineEvent(UserEntity userChange, BusinessProposalEntity proposal, TimelineActivity activity) {
          return new TimelineSaveMessage(userChange, proposal, activity);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BusinessProposalEntity getBusinessProposalEntity() {
        return businessProposalEntity;
    }

    public void setBusinessProposalEntity(BusinessProposalEntity businessProposalEntity) {
        this.businessProposalEntity = businessProposalEntity;
    }

    public TimelineActivity getActivity() {
        return activity;
    }

    public void setActivity(TimelineActivity activity) {
        this.activity = activity;
    }
}
