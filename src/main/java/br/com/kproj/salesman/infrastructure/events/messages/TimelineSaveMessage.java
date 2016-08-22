package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;

public class TimelineSaveMessage {


    private BusinessProposal businessProposal;
    private UserEntity user;
    private TimelineActivity activity;

    public TimelineSaveMessage(UserEntity user, BusinessProposal proposal, TimelineActivity activity) {
        this.user = user;
        this.businessProposal = proposal;
        this.activity = activity;
    }

    public static TimelineSaveMessage createTimelineEvent(UserEntity userChange, BusinessProposal proposal, TimelineActivity activity) {
          return new TimelineSaveMessage(userChange, proposal, activity);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BusinessProposal getBusinessProposal() {
        return businessProposal;
    }

    public void setBusinessProposal(BusinessProposal businessProposal) {
        this.businessProposal = businessProposal;
    }

    public TimelineActivity getActivity() {
        return activity;
    }

    public void setActivity(TimelineActivity activity) {
        this.activity = activity;
    }
}
