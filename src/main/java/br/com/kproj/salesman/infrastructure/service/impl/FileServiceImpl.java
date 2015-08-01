package br.com.kproj.salesman.infrastructure.service.impl;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.files.FileSystemHelper;
import br.com.kproj.salesman.infrastructure.repository.AppFileRepository;
import br.com.kproj.salesman.infrastructure.service.FileService;
import br.com.kproj.salesman.infrastructure.validators.AppFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FileServiceImpl implements FileService {

	
	private @Autowired
    AppFileRepository repository;
	
	private @Autowired
    FileSystemHelper fileSystemHelper;

	private @Autowired
    AppFileValidator appFileValidator;
	
	@Override
	public byte[] getFile(Identifiable entity, AppFile appFile) {
		
		if (appFile == null || appFile.isNew() || entity == null || entity.isNew()) {
			throw new IllegalArgumentException("invalid.arguments.entity.or.appfile.cannot.be.null");
		}
		
		AppFile appFileLoaded = repository.findOne(appFile.getId());
		
		byte[] file = this.fileSystemHelper.getFile(entity, appFileLoaded);
		
		return file;
	}

	@Override
	public Set<String> saveFile(Identifiable entity, AppFile appFile) {
		
		Set<String> errors = this.appFileValidator.validateToSave(appFile);

		if (!errors.isEmpty()) {
			return errors;
		}
		
		if (entity == null || entity.isNew()) {
			errors.add("save.file.entity.not.have.id");
			return errors;
		}
		
		String basePath = this.fileSystemHelper.getBasePath(entity);
		this.fileSystemHelper.mkdirs(basePath);
		
		String fullPathFile = this.fileSystemHelper.getPathFile(entity, appFile);
		
		this.fileSystemHelper.writeFile(fullPathFile, appFile.getFile());
		
		return errors;
	}

}
