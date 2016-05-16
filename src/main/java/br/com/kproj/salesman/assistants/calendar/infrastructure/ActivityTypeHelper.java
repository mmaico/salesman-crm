package br.com.kproj.salesman.assistants.calendar.infrastructure;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.ActivityType;
import br.com.kproj.salesman.infrastructure.repository.ActivityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityTypeHelper {

    @Autowired
    private ActivityTypeRepository repository;



    public Iterable<ActivityType> findAll() {
        return repository.findAll();
    }
}
