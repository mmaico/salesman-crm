package br.com.kproj.salesman.negotiation.view.dto;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.Product;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BusinessProposalDTOTest {

    @InjectMocks
    private BusinessProposalDTO businessProposalDTO;

    private BusinessProposal proposal = new BusinessProposal();



    @Test
    public void shouldBuildBusinessProposal() {
        ReflectionTestUtils.setField(businessProposalDTO, "proposal", proposal);
        List<SaleableItemDTO> saleableItemDTOs = getSaleableItemDTOs();
        businessProposalDTO.setItems(saleableItemDTOs);

        BusinessProposal businessProposal = businessProposalDTO.get();

        assertThat(businessProposal.getSaleableItems().size(), Matchers.is(2));
    }

    public List<SaleableItemDTO> getSaleableItemDTOs() {
        SaleableItemDTO itemOne = new SaleableItemDTO();
        itemOne.setPrice(BigDecimal.TEN);
        itemOne.setQuantity(2);
        itemOne.setSaleableUnit(new Product(1l));
        itemOne.setIpackage(new SalePackage(7l));

        SaleableItemDTO itemTwo = new SaleableItemDTO();
        itemTwo.setPrice(BigDecimal.ONE);
        itemTwo.setQuantity(1);
        itemTwo.setSaleableUnit(new Product(2l));
        itemTwo.setIpackage(new SalePackage(8l));

        SaleableItemDTO itemThree = new SaleableItemDTO();
        itemThree.setPrice(BigDecimal.ZERO);
        itemThree.setQuantity(1);
        itemThree.setIpackage(new SalePackage(8l));

        return Lists.newArrayList(itemOne, itemTwo, itemThree);

    }
}