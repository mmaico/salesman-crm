package br.com.kproj.salesman.medias.media.application.validators;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.medias.media.domain.media.MediaInStorage;
import br.com.kproj.salesman.medias.media.domain.media.MediaValidator;
import br.com.kproj.salesman.medias.media.domain.storage.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.medias.media.application.validators.MediaIgnoreRules.ruleFile;
import static br.com.kproj.salesman.medias.media.application.validators.MediaIgnoreRules.ruleStorageName;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class MediaBusinessRules implements MediaValidator {


    private StorageRepository repository;

    Map<RuleKey, CheckRule<MediaInStorage>> persistRules = new HashMap<>();
    {
        persistRules.put(ruleFile(), (mediaInStorage) ->
            mediaInStorage.getFileContentRaw() == null || isBlank(mediaInStorage.getFileContentRaw().getFile())
        );

        persistRules.put(ruleStorageName(), (mediaInStorage) -> {
            String name = mediaInStorage.getStorage().getName();

            return isBlank(name) || !repository.exists(name);
        });
    }

    @Autowired
    public MediaBusinessRules(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void checkRules(MediaInStorage storage) {
        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        return rule.getValue().check(storage);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(ruleKey -> ruleKey.getKey().getName()).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }


}
