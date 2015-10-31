package br.com.kproj.salesman.negotiation.domain.proposal.saleable;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.PackageBusinessRules;
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

        Set<SalePackage> packagesInSaleable = proposalSaleableItems.stream()
                .filter(item -> item.getSalePackageSaleable() != null && item.getSaleableUnit() != null)
                .map(ProposalSaleableItem::getSalePackageSaleable)
                .collect(Collectors.toSet());

        Set<SalePackage> salePackages = proposalSaleableItems.stream()
                .filter(item -> item.getSalePackageSaleable() != null && item.getSaleableUnit() == null)
                .map(ProposalSaleableItem::getSalePackageSaleable)
                .collect(Collectors.toSet());

        if (packagesInSaleable.size() > salePackages.size()) {
            violations.add("invalid.quantity.packages.inlist.with.packages.insaleable");
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
