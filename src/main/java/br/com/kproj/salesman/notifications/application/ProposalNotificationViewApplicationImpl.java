package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.notification.NotificationView;
import br.com.kproj.salesman.infrastructure.entity.notification.ProposalNotificationView;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.ProposalNotificationViewRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProposalNotificationViewApplicationImpl extends BaseModelServiceImpl<ProposalNotificationView> implements ProposalNotificationViewApplication {


    @Autowired
    private ProposalNotificationViewRepository repository;



    @Override
    public BaseRepository<ProposalNotificationView, Long> getRepository() {
        return repository;
    }


    @Override
    public Optional<NotificationView> register(NotificationView view) {
        if(view.getUser() == null || view.getUser().isNew()) return Optional.empty();

        Optional<ProposalNotificationView> result = repository.findByUser(view.getUser());

        if (result.isPresent()) {
            result.get().setLastVisualization(new Date());
            return result;
        } else {
            view.setLastVisualization(new Date());
            return Optional.of(super.save(view));
        }
    }
}
