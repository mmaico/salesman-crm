package br.com.kproj.salesman.notifications.logview.domain.model.view;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.notifications.logview.domain.model.user.User;
import com.trex.shared.annotations.Model;

import java.util.Date;

@Model
public class ViewInfo extends ModelIdentifiable {

    private Long id;

    private User user;

    private Date visualization;

    private NotificationType type;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getVisualization() {
        return visualization;
    }

    public void setVisualization(Date visualization) {
        this.visualization = visualization;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
