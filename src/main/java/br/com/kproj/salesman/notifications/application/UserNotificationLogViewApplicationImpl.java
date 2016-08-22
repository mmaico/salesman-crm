package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.repository.UserNotificationLogViewRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserNotificationLogViewApplicationImpl extends BaseModelServiceLegacyImpl<UserNotificationLogView> implements UserNotificationLogViewApplication {


    @Autowired
    private UserNotificationLogViewRepository repository;



    public BaseRepositoryLegacy<UserNotificationLogView, Long> getRepository() {
        return repository;
    }


    @Override
    public Optional<UserNotificationLogView> register(UserNotificationLogView view) {
        if(view.getUser() == null || view.getUser().isNew()) return Optional.empty();

        view.setLastVisualization(new Date());
        return Optional.of(super.save(view));
    }

    @Override
    public Optional<UserNotificationLogView> getLastViewProposalNotification(UserEntity user) {

        Page<UserNotificationLogView> result = repository.findLastVisualization(user, UserNotificationLogView.TypeLogView.PROPOSAL_NOTIFICATION, Pager.build().one());

        return Optional.ofNullable(result.getContent().size() > 0 ? result.getContent().get(0) : null);
    }

    @Override
    public Optional<UserNotificationLogView> getLastViewTaskNotification(UserEntity user) {
        Page<UserNotificationLogView> result = repository.findLastVisualization(user, UserNotificationLogView.TypeLogView.TASK_NOTIFICATION, Pager.build().one());

        return Optional.ofNullable(result.getContent().size() > 0 ? result.getContent().get(0) : null);
    }
}
