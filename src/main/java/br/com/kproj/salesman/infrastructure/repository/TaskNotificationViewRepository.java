package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.notification.ProposalNotificationView;
import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotificationView;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaskNotificationViewRepository extends BaseRepository<TaskNotificationView, Long> {

    Optional<TaskNotificationView> findByUser(@Param("user") User user);
}
