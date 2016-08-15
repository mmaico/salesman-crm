package br.com.kproj.salesman.infrastructure.service;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AppFileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.validators.AppFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class AppFileApplicationImpl extends BaseModelServiceLegacyImpl<AppFile> implements AppFileApplication {

    @Autowired
    private AppFileRepository repository;

    @Autowired
    private FilePersistHelper filePersist;

    @Autowired
    private AppFileValidator validator;

    public AppFile save(Identifiable identifiable, AppFile appFile) {

        hasErrors(validator.hasFileAndRequiredInfos(appFile))
                .throwing(ValidationException.class);

        AppFile appFileSaved = super.save(appFile);
        filePersist.saveFile(identifiable, appFileSaved);
        return appFileSaved;
    }

    @Override
    public BaseRepositoryLegacy<AppFile, Long> getRepository() {
        return repository;
    }
}
