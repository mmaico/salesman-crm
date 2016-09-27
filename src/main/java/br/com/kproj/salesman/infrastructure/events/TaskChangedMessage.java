package br.com.kproj.salesman.infrastructure.events;

public class TaskChangedMessage {

    private final Long userWhoChangedId;

    private final String before;
    private final String after;

    public TaskChangedMessage(Long userWhoChangedId,
                              String before, String after) {
        this.userWhoChangedId = userWhoChangedId;
        this.before = before;
        this.after = after;
    }

    public static TaskChangedMessage newChanged(Long userWhoChangedId, String negotiationBefore, String negotiationAfter) {
        return new TaskChangedMessage(userWhoChangedId, negotiationBefore, negotiationAfter);
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
}
