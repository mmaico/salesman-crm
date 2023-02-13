package br.com.kproj.salesman.notifications.logview.domain.model.user;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.notifications.logview.domain.model.view.ViewInfo;
import br.com.kproj.salesman.notifications.logview.domain.model.view.ViewInfoRepository;
import br.com.kproj.salesman.notifications.logview.domain.service.SawNotificationTypeService;

import com.github.mmaico.shared.annotations.Model;
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

    public SawNotificationTypeService sawNotifications() {
        return (type -> (() -> {
            ViewInfo viewInfo = createView()
                        .withType(type).visualizationNow()
                        .withUser(this).build();

            repository.save(viewInfo);
        }));
    }

}
