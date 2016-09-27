package br.com.kproj.salesman.notifications.approval.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepositorySpringdata extends BaseRepositoryLegacy<ApprovalBusinessProposalNotification, Long> {

    @Query("SELECT abpn FROM ApprovalBusinessProposalNotification AS abpn WHERE abpn.notified = :user " +
            " ORDER BY abpn.createDate DESC")
    List<ApprovalBusinessProposalNotification> findProposalNotificationBy(@Param("user") UserEntity user);

}
