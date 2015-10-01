package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FileService {

	byte[] getFile(Identifiable entity, AppFile appFile);

    Optional<AppFile> getAppfile(Long idtAppFile);
	
	Set<String> saveFile(Identifiable entity, AppFile appFile);

    Set<String> saveFile(Identifiable entity, List<AppFile> appFiles);
}
