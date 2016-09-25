package br.com.kproj.salesman.negotiation.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.domain.model.operation.Region;
import br.com.kproj.salesman.negotiation.domain.model.operation.RegionBuilder;
import org.springframework.stereotype.Component;

@Component
public class OperationRegionEntityToOperationRegionConverter implements Converter<OperationRegionEntity, Region> {

    @Override
    public Region convert(OperationRegionEntity operationRegionEntity, Object... args) {
        return RegionBuilder.createRegion(operationRegionEntity.getId()).build();
    }
}
