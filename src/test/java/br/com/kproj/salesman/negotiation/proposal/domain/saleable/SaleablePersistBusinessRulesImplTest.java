package br.com.kproj.salesman.negotiation.proposal.domain.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SaleablePersistBusinessRulesImplTest {

    @InjectMocks
    private SaleablePersistBusinessRulesImpl rules;

    @Mock
    private SaleableUnitRepository salebleRepository;

    @Test
    public void shouldReturnTrueWhenAllValid() {
        SaleableUnitEntity saleableUnit = getSaleableUnits().get(0);

        given(salebleRepository.exists(1l)).willReturn(Boolean.TRUE);

        Boolean result = rules.verifyRules(saleableUnit);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenSaleableUnitNotHaveId() {
        SaleableUnitEntity saleableUnit = getSaleableUnits().get(0);
        saleableUnit.setId(null);

        rules.verifyRules(saleableUnit);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenSaleableUnitNotFound() {
        SaleableUnitEntity saleableUnit = getSaleableUnits().get(0);
        saleableUnit.setId(1l);

        given(salebleRepository.exists(1l)).willReturn(Boolean.FALSE);
        rules.verifyRules(saleableUnit);
    }

    @Test
    public void shouldThrowExceptionWhenSaleableUnitNotFoundAndReturnMessage() {
        Boolean validationExceptionValidation = Boolean.FALSE;
        SaleableUnitEntity saleableUnit = getSaleableUnits().get(0);
        saleableUnit.setId(1l);

        given(salebleRepository.exists(1l)).willReturn(Boolean.FALSE);

        try {
            rules.verifyRules(saleableUnit);
        } catch(ValidationException e) {
            validationExceptionValidation = Boolean.TRUE;
            assertThat(e.getErrors().size(), is(1));
            assertThat(e.getErrors().iterator().next(), is("domain.saleable.item.notexist"));
        }

        assertThat(validationExceptionValidation, is(Boolean.TRUE));
    }

    public List<SaleableUnitEntity> getSaleableUnits() {
        SaleableUnitEntity saleableUnitOne = createSaleableUnit(1l).build();
        SaleableUnitEntity saleableUnitTwo = createSaleableUnit(2l).build();


        return Lists.newArrayList(saleableUnitOne, saleableUnitTwo);
    }

}