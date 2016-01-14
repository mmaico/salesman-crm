package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.notification.ProposalNotificationView;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProposalNotificationViewRepository extends BaseRepository<ProposalNotificationView, Long> {

    Optional<ProposalNotificationView> findByUser(@Param("user") User user);
}
