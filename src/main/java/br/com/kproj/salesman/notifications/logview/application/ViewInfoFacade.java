package br.com.kproj.salesman.notifications.logview.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.notifications.logview.domain.model.view.LastVisualization;
import br.com.kproj.salesman.notifications.logview.domain.model.view.ViewInfo;

import java.util.Optional;

public interface ViewInfoFacade extends ModelFacade<ViewInfo> {

    Optional<ViewInfo> findOne(LastVisualization lastVisualization);
}
