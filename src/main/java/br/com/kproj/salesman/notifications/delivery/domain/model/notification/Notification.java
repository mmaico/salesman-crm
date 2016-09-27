package br.com.kproj.salesman.notifications.delivery.domain.model.notification;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.notifications.delivery.domain.model.task.Task;
import br.com.kproj.salesman.notifications.delivery.domain.model.user.Receiver;
import com.trex.shared.annotations.Model;

import java.util.Date;

@Model
public class Notification extends ModelIdentifiable {

    private Long id;

    private Date createDate;

    private Receiver receiver;

    private Task task;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
