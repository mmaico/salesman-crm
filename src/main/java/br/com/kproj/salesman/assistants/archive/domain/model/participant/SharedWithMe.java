package br.com.kproj.salesman.assistants.archive.domain.model.participant;


import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class SharedWithMe implements ValueObject {

    private final Participant participant;

    public SharedWithMe(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipant() {
        return participant;
    }
}
