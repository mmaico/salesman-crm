package br.com.kproj.salesman.notifications2.logview.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogViewEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.notifications2.logview.domain.model.user.LastViewSpecification;
import br.com.kproj.salesman.notifications2.logview.domain.model.user.User;
import br.com.kproj.salesman.notifications2.logview.domain.model.view.NotificationType;
import br.com.kproj.salesman.notifications2.logview.domain.model.view.ViewInfo;
import br.com.kproj.salesman.notifications2.logview.domain.model.view.ViewInfoRepository;
import br.com.kproj.salesman.notifications2.logview.infrastructure.persistence.springdata.UserNotificationLogViewRepositorySpringdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static br.com.kproj.salesman.notifications2.logview.domain.model.view.ViewInfoBuilder.createView;

@Repository
public class ViewInfoRepositoryHibernate extends BaseRespositoryImpl<ViewInfo, UserNotificationLogViewEntity> implements ViewInfoRepository {

    @Autowired
    private UserNotificationLogViewRepositorySpringdata repository;


    public Optional<ViewInfo> save(ViewInfo entity) {
        UserNotificationLogViewEntity logViewEntity = new UserNotificationLogViewEntity();
        logViewEntity.setLastVisualization(entity.getVisualization());
        logViewEntity.setUser(new UserEntity(entity.getUser().getId()));
        UserNotificationLogViewEntity.TypeLogView typeLogView = UserNotificationLogViewEntity.TypeLogView.valueOf(entity.getType().name());
        logViewEntity.setTypeLogView(typeLogView);

        UserNotificationLogViewEntity result = repository.save(logViewEntity);

        return Optional.of(getConverter().convert(result));
    }

    @Override
    public Optional<ViewInfo> findOne(LastViewSpecification spec) {
        User user = spec.getUser();
        NotificationType type = spec.getType();
        UserNotificationLogViewEntity.TypeLogView typeLogView = UserNotificationLogViewEntity.TypeLogView.valueOf(type.name());

        Page<UserNotificationLogViewEntity> visualizations = repository.findLastVisualization(new UserEntity(user.getId()), typeLogView, Pager.build().one());

        if (visualizations.getContent().isEmpty()) {
            return Optional.empty();
        } else {
            ViewInfo viewFound = getConverter().convert(visualizations.getContent().get(0));
            return Optional.of(viewFound);
        }
    }

    @Override
    public BaseRepositoryLegacy<UserNotificationLogViewEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserNotificationLogViewEntity, ViewInfo> getConverter() {
        return (logView, args) -> {
            NotificationType notificationType = NotificationType.valueOf(logView.getTypeLogView().name());

            return createView(logView.getId())
                        .visualization(logView.getLastVisualization())
                        .withType(notificationType)
                        .withUser(new User(logView.getUser().getId())).build();
        };
    }


}
