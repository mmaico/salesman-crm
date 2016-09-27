package br.com.kproj.salesman.auditing.delivery.domain.model.audit;

import br.com.kproj.salesman.auditing.delivery.domain.model.task.Task;
import br.com.kproj.salesman.auditing.delivery.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.Date;

@Model
public class Auditing extends ModelIdentifiable {

    private Long id;
    private Task task;
    private String data;
    private Date lastUpdate;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
