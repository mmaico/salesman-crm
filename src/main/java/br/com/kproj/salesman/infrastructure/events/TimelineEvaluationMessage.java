package br.com.kproj.salesman.infrastructure.events;


public class TimelineEvaluationMessage {

    private final Long negotiationId;
    private final Long approverId;
    private final String status;

    public TimelineEvaluationMessage(Long negotiationId, Long approverId, String status) {
        this.negotiationId = negotiationId;
        this.approverId = approverId;
        this.status = status;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public Long getApproverId() {
        return approverId;
    }

    public String getStatus() {
        return status;
    }
}
