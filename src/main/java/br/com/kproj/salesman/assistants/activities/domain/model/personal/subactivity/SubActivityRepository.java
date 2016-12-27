package br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubActivityRepository extends BaseRepository<SubActivity, Long> {


    Optional<SubActivity> register(SubActivity activity);

    Page<SubActivity> findAll(RootActivity rootActivity, Pageable pageable);

}
