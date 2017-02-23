package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

public interface AppFileApplication extends ModelLegacyService<AppFileEntity> {

	AppFileEntity save(Identifiable identifiable, AppFileEntity appFileEntity);

}
