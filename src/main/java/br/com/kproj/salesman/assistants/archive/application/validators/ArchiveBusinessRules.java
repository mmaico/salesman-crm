package br.com.kproj.salesman.assistants.archive.application.validators;

import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.assistants.archive.domain.model.archive.ArchiveValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.isBlank;


@Component
public class ArchiveBusinessRules implements ArchiveValidator {

    Map<String, CheckRule<Archive>> rules = new HashMap<>();
    {
        rules.put("archive.without.title", archive -> isBlank(archive.getTitle()));
        rules.put("new.archive.without.file", archive ->
                archive.isNew() && (archive.getPhysical() == null
                        || archive.getPhysical().getFile() == null
                        || archive.getPhysical().getFile().length == 0));

    }

    @Override
    public void checkRules(Archive archive) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(archive))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
