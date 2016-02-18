package br.com.kproj.salesman.assistants.activities.infrastructure;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklist;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivityChecklistRepository extends BaseRepository<ActivityChecklist, Long> {

    @Query("SELECT c FROM ActivityChecklist AS c WHERE c.activity = :activity")
    List<ActivityChecklist> findCheckListBy(@Param("activity") PersonalActivity activity);

    @Query("SELECT c FROM ActivityChecklist AS c WHERE c.id = :id")
    Optional<ActivityChecklist> getOne(@Param("id")Long id);

}
