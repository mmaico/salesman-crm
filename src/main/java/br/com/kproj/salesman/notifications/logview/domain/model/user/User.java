package br.com.kproj.salesman.notifications.logview.domain.model.user;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.notifications.logview.domain.model.view.NotificationType;
import br.com.kproj.salesman.notifications.logview.domain.model.view.ViewInfo;
import br.com.kproj.salesman.notifications.logview.domain.model.view.ViewInfoRepository;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;
import static br.com.kproj.salesman.notifications.logview.domain.model.view.ViewInfoBuilder.createView;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ViewInfoRepository repository;

    public User() {
        autowire(this);
    }
    public User(Long id) {
        this();
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User sawNotifications() {
        return this;
    }

    public User of(NotificationType type) {
        context.add(NotificationType.class, type);
        return this;
    }

    public void now() {
        NotificationType type = (NotificationType) context.get(NotificationType.class);
        ViewInfo viewInfo = createView().withType(type).visualizationNow().withUser(this).build();

        repository.save(viewInfo);
    }
}
