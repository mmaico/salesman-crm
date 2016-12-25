package br.com.kproj.salesman.assistants.calendar.view.support.update;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class ActivityUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public ActivityUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Activity activity)  {

        activity.addField("title", when(body(request).has("title")));
        activity.addField("description", when(body(request).has("description")));
        activity.addField("location", when(body(request).has("location")));
        activity.addField("allDay", when(body(request).has("allDay")));
        activity.addField("start", when(body(request).has("start")));
        activity.addField("end", when(body(request).has("end")));

    }
}
