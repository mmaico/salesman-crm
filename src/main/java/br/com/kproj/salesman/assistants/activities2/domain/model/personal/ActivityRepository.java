package br.com.kproj.salesman.assistants.activities2.domain.model.personal;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface ActivityRepository extends BaseRepository<Activity, Long> {

    Optional<SubActivity> save(SubActivity subActivity);

    void changeStatus(Activity activity, Status newStatus);
}
