package br.com.kproj.salesman.infrastructure.service;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.repository.AppFileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.validators.AppFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppFileApplicationImpl extends BaseModelServiceImpl<AppFile> implements AppFileApplication {

    @Autowired
    private AppFileRepository repository;

    @Autowired
    private FilePersistHelper filePersist;

    @Autowired
    private AppFileValidator validator;

    public AppFile save(Identifiable identifiable, AppFile appFile) {

        validator.hasFileAndRequiredInfos(appFile);

        AppFile appFileSaved = super.save(appFile);
        filePersist.saveFile(identifiable, appFileSaved);
        return appFileSaved;

    }

    @Override
    public BaseRepository<AppFile, Long> getRepository() {
        return repository;
    }
}
