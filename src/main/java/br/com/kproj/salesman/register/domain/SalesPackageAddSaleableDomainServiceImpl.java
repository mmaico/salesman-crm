package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableType;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import br.com.kproj.salesman.register.domain.contract.SalesPackageAddSaleableDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class SalesPackageAddSaleableDomainServiceImpl implements SalesPackageAddSaleableDomainService {

	@Autowired
	private SaleableApplication application;

	Map<String, CheckRule<SaleableUnit>> persistRules = new HashMap<>();

	{
		persistRules.put(description("salespackage.not.exists"), (salespackage) -> salespackage == null || salespackage.isNew() ||
				!application.getOne(salespackage.getId()).isPresent()
		);

		persistRules.put(description("salespackage.not.permit.add.package"), (salespackage) ->
				application.getOne(salespackage.getId()).get().getType() == SaleableType.PACKAGE
		);
	}


	@Override
	public void checkBusinessRulesFor(SaleableUnit saleableUnit) {

		Set<String> violations = persistRules.entrySet()
				.stream()
				.filter(e -> e.getValue().check(saleableUnit))
				.map(Map.Entry::getKey).collect(Collectors.toSet());

		hasErrors(violations).throwing(ValidationException.class);
	}



}
