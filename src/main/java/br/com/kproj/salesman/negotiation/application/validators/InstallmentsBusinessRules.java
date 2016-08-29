package br.com.kproj.salesman.negotiation.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.negotiation.domain.model.payment.InstallmentItem;
import br.com.kproj.salesman.negotiation.domain.model.payment.InstallmentValidate;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNegativeNumber;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class InstallmentsBusinessRules implements InstallmentValidate {


    private Map<String, CheckRule<InstallmentItem>> persistRules = new HashMap<>();
    {

        persistRules.put(description("installment.with.value.less.than.zero"), (installment) -> isNegativeNumber(installment.getValue()));
        persistRules.put(description("proposal.verify.payment.has.invalid.due.date"),
                (installment) -> !(installment.getDueDate() == null) || installment.getDueDate().before(new Date()));
    }

    @Override
    public Boolean checkRules(Collection<InstallmentItem> items) {
        Set<String> violations = Sets.newHashSet();

        if (isEmptySafe(items)) {
            throw new ValidationException(Sets.newHashSet("negotiationold.must.be.payment"));
        }

        for (InstallmentItem item: items) {
            Set<String> result = persistRules.entrySet()
                    .stream()
                    .filter(e -> e.getValue().check(item))
                    .map(Map.Entry::getKey).collect(Collectors.toSet());
            violations.addAll(result);
        }

        hasErrors(violations).throwing(ValidationException.class);

        return Boolean.TRUE;
    }
}
