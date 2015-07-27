package br.com.kproj.salesman.negotiation.domain.proposal.product.impl;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalProductItem;
import br.com.kproj.salesman.infrastructure.repository.ProductRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.product.ProductItemPersistBusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNotNegativeNumber;

@Service
public class ProductItemPersistBusinessRulesImpl implements ProductItemPersistBusinessRules {

    @Autowired
    private ProductRepository productRepository;

    public Boolean verifyRules(BusinessProposal businessProposal) {

        List<ProposalProductItem> result = businessProposal.getProductItems()
                .stream()
                .filter(e ->
                        !productRepository.exists(e.getProduct().getId())
                                || !isNotNegativeNumber(e.getPrice())
                                || !(e.getQuantity() > 0))
                .collect(Collectors.toList());

        return result.isEmpty();
    }
}
