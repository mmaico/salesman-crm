package br.com.kproj.salesman.notifications.delivery.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotification;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskNotificationRepositorySpringdata extends BaseRepositoryLegacy<TaskNotification, Long> {

    @Query("SELECT abpn FROM TaskNotification AS abpn WHERE abpn.notified = :user ORDER BY abpn.createDate DESC")
    List<TaskNotification> findTaskNotificationBy(@Param("user") UserEntity user);

}
