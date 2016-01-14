package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotificationView;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.TaskNotificationViewRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TaskNotificationViewApplicationImpl extends BaseModelServiceImpl<TaskNotificationView> implements TaskNotificationViewApplication {


    @Autowired
    private TaskNotificationViewRepository repository;



    @Override
    public BaseRepository<TaskNotificationView, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<TaskNotificationView> register(TaskNotificationView view) {
        if(view.getUser() == null || view.getUser().isNew()) return Optional.empty();

        Optional<TaskNotificationView> result = repository.findByUser(view.getUser());

        if (result.isPresent()) {
            result.get().setLastVisualization(new Date());
            return result;
        } else {
            view.setLastVisualization(new Date());
           return Optional.of(super.save(view));
        }

    }
}
