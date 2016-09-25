package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogViewEntity;
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
public class UserNotificationLogViewApplicationImpl extends BaseModelServiceLegacyImpl<UserNotificationLogViewEntity> implements UserNotificationLogViewApplication {


    @Autowired
    private UserNotificationLogViewRepository repository;



    public BaseRepositoryLegacy<UserNotificationLogViewEntity, Long> getRepository() {
        return repository;
    }


    @Override
    public Optional<UserNotificationLogViewEntity> register(UserNotificationLogViewEntity view) {
        if(view.getUser() == null || view.getUser().isNew()) return Optional.empty();

        view.setLastVisualization(new Date());
        return Optional.of(super.save(view));
    }

    @Override
    public Optional<UserNotificationLogViewEntity> getLastViewProposalNotification(UserEntity user) {

        Page<UserNotificationLogViewEntity> result = repository.findLastVisualization(user, UserNotificationLogViewEntity.TypeLogView.PROPOSAL_NOTIFICATION, Pager.build().one());

        return Optional.ofNullable(result.getContent().size() > 0 ? result.getContent().get(0) : null);
    }

    @Override
    public Optional<UserNotificationLogViewEntity> getLastViewTaskNotification(UserEntity user) {
        Page<UserNotificationLogViewEntity> result = repository.findLastVisualization(user, UserNotificationLogViewEntity.TypeLogView.TASK_NOTIFICATION, Pager.build().one());

        return Optional.ofNullable(result.getContent().size() > 0 ? result.getContent().get(0) : null);
    }
}
