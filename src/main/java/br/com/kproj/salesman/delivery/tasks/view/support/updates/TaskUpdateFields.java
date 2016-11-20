package br.com.kproj.salesman.delivery.tasks.view.support.updates;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class TaskUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public TaskUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Task task)  {

        task.addField("title", when(body(request).has("title")));
        task.addField("description", when(body(request).has("description")));
        task.addField("deadline", when(body(request).has("deadline")));
        task.addField("status", when(body(request).has("status")));

    }
}
