package br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity;


import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface RootActivityRepository extends BaseRepository<RootActivity, Long> {


    Iterable<RootActivity> findAll(Owner owner, Pageable pageable);

    Optional<RootActivity> register(RootActivity activity);

}
