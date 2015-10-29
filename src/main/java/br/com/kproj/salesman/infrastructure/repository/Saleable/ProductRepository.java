package br.com.kproj.salesman.infrastructure.repository.Saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductRepository extends BaseSaleableRepository<Product> {

    @Query("SELECT p FROM Product AS p WHERE p.type = 'PRODUCT' AND p.id = :id")
    Optional<Product> getOne(@Param("id")Long id);
}
