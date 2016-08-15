package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

public interface AppFileApplication extends ModelLegacyService<AppFile> {

	AppFile save(Identifiable identifiable, AppFile appFile);

}
