package br.com.kproj.salesman.infrastructure.events;

public class NegotiationChangedMessage {

    private final Long negotiationId;
    private final Long userWhoChangedId;
    private final String before;
    private final String after;

    public NegotiationChangedMessage(Long negotiationId, Long userWhoChangedId,
                                     String before, String after) {
        this.negotiationId = negotiationId;
        this.userWhoChangedId = userWhoChangedId;
        this.before = before;
        this.after = after;
    }

    public static NegotiationChangedMessage newChanged(Long negotiationId, Long userWhoChangedId, String negotiationBefore, String negotiationAfter) {
        return new NegotiationChangedMessage(negotiationId, userWhoChangedId, negotiationBefore, negotiationAfter);
    }

    public Long getUserWhoChangedId() {
        return userWhoChangedId;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }
}
