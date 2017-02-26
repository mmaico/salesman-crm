package br.com.kproj.salesman.assistants.activities.view.support.update;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component("activityUpdateFieldsActivitiesModule")
public class ActivityUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public ActivityUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Activity activity)  {

        activity.addField("title", when(body(request).has("title")));
        activity.addField("description", when(body(request).has("description")));
        activity.addField("deadline", when(body(request).has("deadline")));
        activity.addField("status", when(body(request).has("status")));
        activity.addField("status", when(body(request).has("status")));
    }
}
