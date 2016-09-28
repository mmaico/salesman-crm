package br.com.kproj.salesman.assistants.archive.domain.model.participant;


import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class ParticipantChangeArchive implements ValueObject {

    private final Participant participant;
    private final Archive archive;


    public ParticipantChangeArchive(Participant participant, Archive archive) {
        this.participant = participant;
        this.archive = archive;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Archive getArchive() {
        return archive;
    }

    public ParticipantChangeArchive createChangeArchive(Participant participant, Archive archive) {
        return new ParticipantChangeArchive(participant, archive);
    }
}
