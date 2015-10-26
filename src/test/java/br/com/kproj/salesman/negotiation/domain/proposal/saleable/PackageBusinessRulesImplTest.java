package br.com.kproj.salesman.negotiation.domain.proposal.saleable;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.Package;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static br.com.kproj.salesman.infrastructure.entity.builders.ProposalSaleableItemBuilder.create;
import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static com.google.common.collect.Lists.newArrayList;

@RunWith(MockitoJUnitRunner.class)
public class PackageBusinessRulesImplTest {

    @InjectMocks
    private PackageBusinessRulesImpl rules;


    


    private BusinessProposal getProposalStub() {
        SaleableUnit saleableUnitOne = createSaleableUnit(1l).build();
        SaleableUnit saleableUnitTwo = createSaleableUnit(2l).build();
        Package ipackage = new Package(2l);

        BusinessProposal businessProposal = new BusinessProposal();

        ProposalSaleableItem proposalSaleableOne = create()
                .withPackage(ipackage)
                .withSaleable(saleableUnitOne)
                .withQuantity(2)
                .withPrice(BigDecimal.TEN).build();

        ProposalSaleableItem proposalSaleableTwo = create()
                .withPackage(ipackage)
                .withSaleable(saleableUnitTwo)
                .withQuantity(1)
                .withPrice(BigDecimal.TEN).build();

        ProposalSaleableItem proposalSaleablePackage = create()
                .withPackage(ipackage)
                .withSaleable(null)
                .withQuantity(1)
                .withPrice(BigDecimal.ZERO).build();

        businessProposal
                .setSaleableItems(newArrayList(proposalSaleableOne, proposalSaleableTwo, proposalSaleablePackage));

        return businessProposal;
    }

}