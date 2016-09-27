package br.com.kproj.salesman.notifications.logview.application;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.notifications.logview.domain.model.user.LastViewSpecification;
import br.com.kproj.salesman.notifications.logview.domain.model.user.User;
import br.com.kproj.salesman.notifications.logview.domain.model.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.notifications.logview.domain.model.user.LastViewSpecification.which;

@Service
public class ViewInfoServiceImpl extends BaseModelServiceImpl<ViewInfo> implements ViewInfoFacade {

    private ViewInfoRepository repository;

    @Autowired
    public ViewInfoServiceImpl(ViewInfoRepository repository) {
        this.repository = repository;
    }

    public void register(UserSawNotifications sawNotifications) {
        User user = sawNotifications.getUser();
        NotificationType type = sawNotifications.getType();

        user.sawNotifications().of(type).now();
    }

    @Override
    public Optional<ViewInfo> findOne(LastVisualization lastViewing) {
        User user = lastViewing.getUser();
        NotificationType type = lastViewing.getType();

        LastViewSpecification spec = which().lastTimeThat(user).viewedNotifications().of(type);
        Optional<ViewInfo> viewInfo = repository.findOne(spec);

        return viewInfo;
    }

    @Override
    public BaseRepository<ViewInfo, Long> getRepository() {
        return repository;
    }


}
