package br.com.kproj.salesman.medias.storage.application.validators;


import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.medias.storage.application.validators.StorageIgnoreRules.ruleInvalidName;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class StorageBusinessRules implements StorageValidator {

    Map<RuleKey, CheckRule<Storage>> persistRules = new HashMap<>();
    {
        persistRules.put(ruleInvalidName(), (storage) -> isBlank(storage.getName()));
    }

    @Override
    public void checkRules(Storage storage) {
        checkRules(storage, StorageIgnoreRules.add(EMPTY));
    }

    @Override
    public void checkRules(Storage storage, StorageIgnoreRules ignoreRules) {
        RulesExecute.runRules(persistRules, storage);
    }
}
