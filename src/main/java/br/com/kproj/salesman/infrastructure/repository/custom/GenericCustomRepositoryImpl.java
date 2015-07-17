package br.com.kproj.salesman.infrastructure.repository.custom;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.repository.GenericCustomRepository;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
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
		
		HibernateQuery query = new HibernateQuery(this.em.unwrap(Session.class));
		HibernateQuery queryCount = new HibernateQuery(this.em.unwrap(Session.class));
		
		HibernateQuery queryDone = query.from(entity)
			.where(predicate)
				.orderBy(orders)
				.offset(page.getOffset()).limit(page.getPageSize());
				
		long count = queryCount.from(entity).where(predicate).count();
		List<T> list = queryDone.list(entity);
		
		return new PageImpl<T>(list, page, count);
	}
	
}
