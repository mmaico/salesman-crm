package br.com.kproj.salesman.infrastructure.repository.custom;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.QPerson;
import br.com.kproj.salesman.infrastructure.repository.GenericCustomRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @Autowired
    private GenericCustomRepository genericCustomRepository;

    @Override
    public Page<Person> findAll(Predicate predicate, Pageable page, OrderSpecifier[] orders) {
        return genericCustomRepository.findAll(QPerson.person, predicate, page, orders);
    }
}
