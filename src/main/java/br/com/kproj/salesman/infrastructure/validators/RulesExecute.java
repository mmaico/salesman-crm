package br.com.kproj.salesman.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.validators.DefaultIgnoreRules.getIgnoreRules;


public class RulesExecute {



    public static <T> void runRules(Map<RuleKey, CheckRule<T>> rules, T model, IgnoreRules ignoreRules) {

        Set<String> errors = rules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        if (ignoreRules.getToIgnore().contains(rule.getKey())) return Boolean.FALSE;

                        if (model instanceof ModelIdentifiable) {
                            ModelIdentifiable modelConverted = (ModelIdentifiable) model;
                            if (modelConverted.getFields().isEmpty()) {
                                return rule.getValue().check(model);
                            } else {
                                if (modelConverted.needPersist(rule.getKey().getField())) {
                                    return rule.getValue().check(model);
                                }
                            }
                            return Boolean.FALSE;
                        }
                        return rule.getValue().check(model);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(item -> item.getKey().getName()).collect(Collectors.toSet());

        hasErrors(errors).throwing(ValidationException.class);
    }

    public static <T> void runRules(Map<RuleKey, CheckRule<T>> rules, T model) {
        runRules(rules, model, getIgnoreRules());
    }


}
