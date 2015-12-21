package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaleableUnitDomainServiceImpl implements SaleableUnitDomainService {

	@Override
	public void checkBusinessRulesFor(SaleableUnit saleableUnit) {
		
		if (BigDecimal.ZERO.compareTo(saleableUnit.getPrice()) > 0) {
            throw new ValidationException(Sets.newHashSet("product.with.invalid.price"));
        }
		
	}

	
}
