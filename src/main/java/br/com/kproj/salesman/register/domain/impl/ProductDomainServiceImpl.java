package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.register.domain.ProductDomainService;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.math.BigDecimal;

@Service
public class ProductDomainServiceImpl implements ProductDomainService {

	@Override
	public void verifyPreconditionToSave(Product product) {
		
		if (BigDecimal.ZERO.compareTo(product.getPrice()) > 0) {
            throw new ValidationException("product.with.invalid.price");
        }
		
	}

	
}
