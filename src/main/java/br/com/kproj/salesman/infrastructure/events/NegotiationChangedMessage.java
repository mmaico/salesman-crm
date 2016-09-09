package br.com.kproj.salesman.infrastructure.events;

public class NegotiationChangedMessage {

    private final Long negotiationId;
    private final Long userWhoChangedId;

    private final String negotiationBefore;
    private final String negotiationAfter;

    public NegotiationChangedMessage(Long negotiationId, Long userWhoChangedId,
                                     String negotiationBefore, String negotiationAfter) {
        this.negotiationId = negotiationId;
        this.userWhoChangedId = userWhoChangedId;
        this.negotiationBefore = negotiationBefore;
        this.negotiationAfter = negotiationAfter;
    }

    public static NegotiationChangedMessage create(Long negotiationId, Long userWhoChangedId, String negotiationBefore, String negotiationAfter) {
        return new NegotiationChangedMessage(negotiationId, userWhoChangedId, negotiationBefore, negotiationAfter);
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public Long getUserWhoChangedId() {
        return userWhoChangedId;
    }

    public String getNegotiationBefore() {
        return negotiationBefore;
    }

    public String getNegotiationAfter() {
        return negotiationAfter;
    }
}
