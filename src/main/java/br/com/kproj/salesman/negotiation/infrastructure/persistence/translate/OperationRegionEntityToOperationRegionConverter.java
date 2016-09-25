package br.com.kproj.salesman.negotiation.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.domain.model.operation.OperationRegion;
import br.com.kproj.salesman.negotiation.domain.model.operation.OperationRegionBuilder;
import org.springframework.stereotype.Component;

@Component
public class OperationRegionEntityToOperationRegionConverter implements Converter<OperationRegionEntity, OperationRegion> {

    @Override
    public OperationRegion convert(OperationRegionEntity operationRegionEntity, Object... args) {
        return OperationRegionBuilder.createRegion(operationRegionEntity.getId()).build();
    }
}
