package br.com.kproj.salesman.negotiation2.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.SaleableItem;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.SaleableItemValidate;
import br.com.kproj.salesman.negotiation2.domain.model.product.SaleableRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNotNegativeNumber;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class SaleableItemBusinessRules implements SaleableItemValidate {

    @Autowired
    private SaleableRepository saleableRepository;


    private Map<String, CheckRule<SaleableItem>> persistRules = new HashMap<>();
    {
        persistRules.put(description("negotiation.item.without.price"), (saleable) -> isNotNegativeNumber(saleable.getPrice()));
        persistRules.put(description("negotiation.item.without.quantity"), (saleable) -> saleable.getQuantity() > 0);
        persistRules.put(description("negotiation.item.invalid.original.price"), (saleable) -> saleable.getOriginalPrice() != null);

        persistRules.put(description("negotiation.saleable.item.wihtout.id"), (saleable) -> saleable == null || saleable.isNew());
        persistRules.put(description("negotiation.saleable.item.notexist"), (saleable) -> {
            if (saleable.hasPackage()) {
                return !saleableRepository.findOne(saleable.getSaleablePackage().getId()).isPresent();
            } else {
                return !saleableRepository.findOne(saleable.getSaleable().getId()).isPresent();
            }
        });
    }

    @Override
    public Boolean checkRules(Collection<SaleableItem> items) {
        Set<String> violations = Sets.newHashSet();

        if (isEmptySafe(items)) {
            throw new ValidationException(Sets.newHashSet("negotiation.must.be.products"));
        }

        for (SaleableItem item: items) {
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
