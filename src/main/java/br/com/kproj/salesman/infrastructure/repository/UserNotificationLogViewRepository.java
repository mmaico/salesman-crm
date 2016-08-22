package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserNotificationLogViewRepository extends BaseRepositoryLegacy<UserNotificationLogView, Long> {

    @Query("SELECT view FROM UserNotificationLogView AS view WHERE view.user = :user AND view.typeLogView = :logType ORDER BY view.lastVisualization DESC")
    Page<UserNotificationLogView> findLastVisualization(@Param("user") UserEntity user, @Param("logType") UserNotificationLogView.TypeLogView logType, Pageable page);
}
