package br.com.kproj.salesman.infrastructure.repository.Saleable;


import br.com.kproj.salesman.infrastructure.entity.saleable.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ServiceRepository extends BaseSaleableRepository<Service> {

    @Query("SELECT s FROM Service AS s WHERE s.type = 'SERVICE' AND s.id = :id")
    Optional<Service> getOne(@Param("id")Long id);
}
