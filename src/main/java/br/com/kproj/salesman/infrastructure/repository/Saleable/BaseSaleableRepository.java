package br.com.kproj.salesman.infrastructure.repository.Saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseSaleableRepository<T extends SaleableUnit> extends BaseRepository<T, Long> {

}
