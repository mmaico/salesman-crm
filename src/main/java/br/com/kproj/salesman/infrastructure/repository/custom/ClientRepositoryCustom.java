package br.com.kproj.salesman.infrastructure.repository.custom;


import br.com.kproj.salesman.infrastructure.entity.Client;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ClientRepositoryCustom {

    Page<Client> findAll(Predicate predicate, Pageable page, OrderSpecifier<?>... orders);
}
