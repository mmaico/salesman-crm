package br.com.kproj.salesman.notifications.logview.domain.model.view;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.notifications.logview.domain.model.user.LastViewSpecification;

import java.util.Optional;

public interface ViewInfoRepository extends BaseRepository<ViewInfo, Long> {


    Optional<ViewInfo> findOne(LastViewSpecification spec);

}

