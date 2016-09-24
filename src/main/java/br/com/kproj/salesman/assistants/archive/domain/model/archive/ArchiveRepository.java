package br.com.kproj.salesman.assistants.archive.domain.model.archive;


import br.com.kproj.salesman.assistants.archive.domain.model.owner.Owner;
import br.com.kproj.salesman.assistants.archive.domain.model.participant.SharedWithMe;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ArchiveRepository extends BaseRepository<Archive, Long> {

    List<Archive> findAll(Owner owner);
    List<Archive> findAll(SharedWithMe shared);
    Optional<Archive> findOne(WithPhysicalFile file);
}
