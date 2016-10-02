package br.com.kproj.salesman.assistants.activities.domain.model.checklist;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;
import java.util.Optional;

public interface ChecklistRepository extends BaseRepository<Checklist, Long> {

    Optional<Checklist> save(Checklist checklist);

    Collection<Checklist> findAll(Activity activity);

    void complete(Checklist checklist);

    Optional<Checklist> newCheckList(Checklist checklist, Activity activity);
}
