package br.com.kproj.salesman.infrastructure.repository.custom;


import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.entity.QClient;
import br.com.kproj.salesman.infrastructure.repository.GenericCustomRepository;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ClientRepositoryImpl implements ClientRepositoryCustom {

    @Autowired
    private GenericCustomRepository genericCustomRepository;

    @Override
    public Page<Client> findAll(Predicate predicate, Pageable page, OrderSpecifier[] orders) {
        return genericCustomRepository.findAll(QClient.client, predicate, page, orders);
    }
}
