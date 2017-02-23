package br.com.kproj.salesman.infrastructure.service;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.files.FileSystemHelper;
import br.com.kproj.salesman.infrastructure.repository.AppFileRepository;
import br.com.kproj.salesman.infrastructure.validators.AppFileValidator;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Service
public class FilePersistHelper {

	
	private @Autowired
    AppFileRepository repository;
	
	private @Autowired
    FileSystemHelper fileSystemHelper;

	private @Autowired
    AppFileValidator appFileValidator;
	
	public byte[] getFile(Identifiable entity, AppFileEntity appFileEntity) {
		
		if (appFileEntity == null || appFileEntity.isNew() || entity == null || entity.isNew()) {
			throw new IllegalArgumentException("invalid.arguments.entity.or.appfile.cannot.be.null");
		}
		
		AppFileEntity appFileEntityLoaded = repository.findOne(appFileEntity.getId());
		
		byte[] file = this.fileSystemHelper.getFile(entity, appFileEntityLoaded);
		
		return file;
	}

	public void saveFile(Identifiable entity, AppFileEntity appFileEntity) {

		this.appFileValidator.hasFileAndRequiredInfos(appFileEntity);

		if (entity == null || entity.isNew()) {
			throw new ValidationException(Sets.newHashSet("add.file.entity.not.have.id"));
		}
		
		String basePath = this.fileSystemHelper.getBasePath(entity);
		this.fileSystemHelper.mkdirs(basePath);
		
		String fullPathFile = this.fileSystemHelper.getPathFile(entity, appFileEntity);
		
		this.fileSystemHelper.writeFile(fullPathFile, appFileEntity.getFile());
	}

	public void saveFile(Identifiable entity, List<AppFileEntity> appFileEntities) {

        if (isEmptySafe(appFileEntities)) return;

        appFileEntities.stream().forEach(e -> saveFile(entity, e));
    }

}
