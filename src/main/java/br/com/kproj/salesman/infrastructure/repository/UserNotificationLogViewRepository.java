package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserNotificationLogViewRepository extends BaseRepository<UserNotificationLogView, Long> {

    @Query("SELECT view FROM UserNotificationLogView AS view WHERE view.user = :user AND view.type = :type ORDER BY view.lastVisualization DESC")
    Optional<UserNotificationLogView> findLastVisualization(@Param("user") User user, @Param("type") UserNotificationLogView.TypeLogView type, Pageable page);
}
