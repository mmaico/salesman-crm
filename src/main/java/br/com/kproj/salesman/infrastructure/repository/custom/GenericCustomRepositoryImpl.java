package br.com.kproj.salesman.infrastructure.repository.custom;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.repository.GenericCustomRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class GenericCustomRepositoryImpl<T extends Identifiable> implements GenericCustomRepository<T> {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Page<T> findAll(EntityPath<T> entity, Predicate predicate, Pageable page, OrderSpecifier<?>... orders) {

		HibernateQueryFactory queryFactory = new HibernateQueryFactory(this.em.unwrap(Session.class));

		List<T> allresult = queryFactory.selectFrom(entity)
				.where(predicate)
				.orderBy(orders)
				.offset(page.getOffset()).limit(page.getPageSize())
				.fetch();

		long count = queryFactory.from(entity).where(predicate).fetchCount();
		
		return new PageImpl<>(allresult, page, count);
	}
	
}
