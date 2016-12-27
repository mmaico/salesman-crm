package br.com.kproj.salesman.assistants.activities.domain.model.personal;


import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

public interface ActivityRepository extends BaseRepository<Activity, Long> {

    Activity update(Activity activity);

     Iterable<Activity> findAll(Owner owner, Pageable pageable);


}
