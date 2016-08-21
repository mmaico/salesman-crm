package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import br.com.kproj.salesman.register.infrastructure.validators.SaleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class SaleableUnitDomainServiceImpl implements SaleableUnitDomainService {

	@Autowired
	private SaleableValidator validator;


	Map<String, CheckRuleLegacy<SaleableUnitEntity>> persistRules = new HashMap<>();

	{
		persistRules.put(description("product.with.invalid.price"), (saleable) ->
						BigDecimal.ZERO.compareTo(saleable.getPrice()) > 0
				);

		persistRules.put(description("product.with.invalid.infos"), (saleable) -> hasContraintViolated(saleable, validator));
	}

	@Override
	public void checkBusinessRulesFor(SaleableUnitEntity saleableUnit) {

		Set<String> violations = persistRules.entrySet()
				.stream()
				.filter(e -> e.getValue().check(saleableUnit))
				.map(Map.Entry::getKey).collect(Collectors.toSet());

		hasErrors(violations).throwing(ValidationException.class);
		
	}

	
}
