package br.com.kproj.salesman.administration.users.infrastructure.persistence.translator;

import br.com.kproj.salesman.administration.users.domain.model.position.Position;
import br.com.kproj.salesman.administration.users.domain.model.position.PositionBuilder;
import br.com.kproj.salesman.infrastructure.entity.UserPositionEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;


@Component
public class PositionEntityToPositionConverter implements Converter<UserPositionEntity, Position> {

    @Override
    public Position convert(UserPositionEntity positionEntity, Object... args) {
        if (positionEntity == null) return null;
        Position position = PositionBuilder.createPosition(positionEntity.getId())
                .withName(positionEntity.getName()).build();

        return position;
    }
}
