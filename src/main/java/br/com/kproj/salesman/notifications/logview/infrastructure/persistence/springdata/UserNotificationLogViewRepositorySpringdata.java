package br.com.kproj.salesman.notifications.logview.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogViewEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserNotificationLogViewRepositorySpringdata extends BaseRepositoryLegacy<UserNotificationLogViewEntity, Long> {

    @Query("SELECT view FROM UserNotificationLogView AS view WHERE view.user = :user AND view.typeLogView = :logType ORDER BY view.lastVisualization DESC")
    Page<UserNotificationLogViewEntity> findLastVisualization(@Param("user") UserEntity user, @Param("logType") UserNotificationLogViewEntity.TypeLogView logType, Pageable page);
}
