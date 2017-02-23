package br.com.kproj.salesman.infrastructure.service;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AppFileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.validators.AppFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class AppFileApplicationImpl extends BaseModelServiceLegacyImpl<AppFileEntity> implements AppFileApplication {

    @Autowired
    private AppFileRepository repository;

    @Autowired
    private FilePersistHelper filePersist;

    @Autowired
    private AppFileValidator validator;

    public AppFileEntity save(Identifiable identifiable, AppFileEntity appFileEntity) {

        hasErrors(validator.hasFileAndRequiredInfos(appFileEntity))
                .throwing(ValidationException.class);

        AppFileEntity appFileEntitySaved = super.save(appFileEntity);
        filePersist.saveFile(identifiable, appFileEntitySaved);
        return appFileEntitySaved;
    }

    @Override
    public BaseRepositoryLegacy<AppFileEntity, Long> getRepository() {
        return repository;
    }
}
