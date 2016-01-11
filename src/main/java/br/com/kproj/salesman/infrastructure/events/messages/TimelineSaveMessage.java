package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;

public class TimelineSaveMessage {


    private BusinessProposal businessProposal;
    private User user;
    private TimelineActivity activity;

    public TimelineSaveMessage(User user, BusinessProposal proposal, TimelineActivity activity) {
        this.user = user;
        this.businessProposal = proposal;
        this.activity = activity;
    }

    public static TimelineSaveMessage createTimelineEvent(User userChange, BusinessProposal proposal, TimelineActivity activity) {
          return new TimelineSaveMessage(userChange, proposal, activity);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
