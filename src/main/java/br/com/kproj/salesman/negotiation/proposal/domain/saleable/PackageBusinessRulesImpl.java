package br.com.kproj.salesman.negotiation.proposal.domain.saleable;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.proposal.domain.saleable.contract.PackageBusinessRules;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class PackageBusinessRulesImpl implements PackageBusinessRules {




    public Boolean verifyRules(List<ProposalSaleableItem> proposalSaleableItems) {
        Set<String> violations = Sets.newHashSet();

        Set<SalePackageEntity> packagesInSaleable = proposalSaleableItems.stream()
                .filter(item -> item.getSalePackage() != null && item.getSaleableUnit() != null)
                .map(ProposalSaleableItem::getSalePackage)
                .collect(Collectors.toSet());

        Set<SalePackageEntity> salePackages = proposalSaleableItems.stream()
                .filter(item -> item.getSalePackage() != null && item.getSaleableUnit() == null)
                .map(ProposalSaleableItem::getSalePackage)
                .collect(Collectors.toSet());

        if (packagesInSaleable.size() > salePackages.size()) {
            violations.add("package.invalid.quantity.package.in.list.is.distinct.in.products");
            hasErrors(violations).throwing(ValidationException.class);
        }

        long countPackageInReferenceNotExist = packagesInSaleable
                .stream().filter(item -> !salePackages.contains(item)).count();

        if (countPackageInReferenceNotExist > 0) {
            violations.add("invalid.package.inreference.not.exist.in.list");
            hasErrors(violations).throwing(ValidationException.class);
        }

        return Boolean.TRUE;
    }
}
