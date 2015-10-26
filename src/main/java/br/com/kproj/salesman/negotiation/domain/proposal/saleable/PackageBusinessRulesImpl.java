package br.com.kproj.salesman.negotiation.domain.proposal.saleable;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.PackageBusinessRules;
import org.springframework.stereotype.Service;
import br.com.kproj.salesman.infrastructure.entity.saleable.Package;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PackageBusinessRulesImpl implements PackageBusinessRules {




    public Boolean verifyRules(List<ProposalSaleableItem> proposalSaleableItems) {

        Set<Package> packagesInSaleables = proposalSaleableItems.stream()
                .filter(item -> item.getPackageSaleable() != null && item.getSaleableUnit() != null)
                .map(ProposalSaleableItem::getPackageSaleable)
                .collect(Collectors.toSet());

        Set<Package> packages = proposalSaleableItems.stream()
                .filter(item -> item.getPackageSaleable() != null && item.getSaleableUnit() == null)
                .map(ProposalSaleableItem::getPackageSaleable)
                .collect(Collectors.toSet());


        return Boolean.TRUE;
    }
}
