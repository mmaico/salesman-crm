package br.com.kproj.salesman.assistants.archive.application;


import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.assistants.archive.domain.model.owner.OwnerChangeArchive;
import br.com.kproj.salesman.assistants.archive.domain.model.participant.ParticipantChangeArchive;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface ArchiveFacade extends ModelFacade<Archive> {

    Optional<Archive> register(OwnerChangeArchive changeArchive);

    Optional<Archive> register(ParticipantChangeArchive changeArchive);
}
