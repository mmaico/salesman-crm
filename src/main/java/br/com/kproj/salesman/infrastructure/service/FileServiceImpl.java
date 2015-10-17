package br.com.kproj.salesman.infrastructure.service;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.files.FileSystemHelper;
import br.com.kproj.salesman.infrastructure.repository.AppFileRepository;
import br.com.kproj.salesman.infrastructure.validators.AppFileValidator;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

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
    public Optional<AppFile> getAppfile(Long idtAppFile) {

        if (idtAppFile == null) {
            return Optional.empty();
        }

        AppFile appfileLoaded = this.repository.findOne(idtAppFile);

        return Optional.ofNullable(appfileLoaded);
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

    @Override
    public Set<String> saveFile(Identifiable entity, List<AppFile> appFiles) {
        Set<String> errors = Sets.newHashSet();

        if (isEmptySafe(appFiles)) {
            return errors;
        }

        appFiles.stream().forEach(e -> errors.addAll(saveFile(entity, e)));

        return errors;
    }

}
