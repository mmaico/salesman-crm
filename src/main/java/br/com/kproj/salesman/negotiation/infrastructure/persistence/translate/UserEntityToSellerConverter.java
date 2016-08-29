package br.com.kproj.salesman.negotiation.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.domain.model.seller.SellerBuilder;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Component
public class UserEntityToSellerConverter implements Converter<UserEntity, Seller> {

    @Override
    public Seller convert(UserEntity userEntity, Object... args) {

        return autowire(SellerBuilder.createSeller(userEntity.getId()).build());
    }
}
