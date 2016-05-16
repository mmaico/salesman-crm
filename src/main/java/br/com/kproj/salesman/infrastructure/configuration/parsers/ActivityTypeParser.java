package br.com.kproj.salesman.infrastructure.configuration.parsers;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.ActivityType;
import br.com.kproj.salesman.infrastructure.entity.builders.ActivityTypeBuilder;
import com.google.common.collect.Lists;

import java.util.List;


public class ActivityTypeParser {




    public static List<ActivityType> getActivities() {
        List<ActivityType> items = Lists.newArrayList();

        items.add(ActivityTypeBuilder.create("E-Mail").build());
        items.add(ActivityTypeBuilder.create("Telefone").build());
        items.add(ActivityTypeBuilder.create("Atividade").build());
        items.add(ActivityTypeBuilder.create("Reuni&atilde;o").build());
        items.add(ActivityTypeBuilder.create("Outros").build());

        return items;
    }


}
