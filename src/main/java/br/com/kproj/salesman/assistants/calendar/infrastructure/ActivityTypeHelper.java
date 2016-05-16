package br.com.kproj.salesman.assistants.calendar.infrastructure;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.ActivityType;
import br.com.kproj.salesman.infrastructure.entity.builders.ActivityTypeBuilder;
import br.com.kproj.salesman.infrastructure.repository.ActivityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActivityTypeHelper {

    @Autowired
    private ActivityTypeRepository repository;

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("E-Mail", "email");
        map.put("Telefone", "phone");
        map.put("Atividade", "activity");
        map.put("Reuni&atilde;o", "meeting");
        map.put("Outros", "other");
    }


    public Iterable<ActivityType> findAll() {
        return repository.findAll();
    }

    public String getClassName(String name) {
        return map.get(name);
    }
}
