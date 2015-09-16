package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import java.util.List;
import java.util.Set;

public interface FileService {

	byte[] getFile(Identifiable entity, AppFile appFile);
	
	Set<String> saveFile(Identifiable entity, AppFile appFile);

    Set<String> saveFile(Identifiable entity, List<AppFile> appFiles);
}
