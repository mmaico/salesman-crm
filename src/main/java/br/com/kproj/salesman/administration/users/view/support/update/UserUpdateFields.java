package br.com.kproj.salesman.administration.users.view.support.update;

import br.com.kproj.salesman.administration.users.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class UserUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public UserUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(User user)  {

        user.addField("login", when(body(request).has("login")));
        user.addField("password", when(body(request).has("password")));
        user.addField("name", when(body(request).has("name")));
        user.addField("lastname", when(body(request).has("lastname")));
        user.addField("email", when(body(request).has("email")));
        user.addField("branch", when(body(request).has("branch")));
        user.addField("position", when(body(request).has("position")));

    }
}
