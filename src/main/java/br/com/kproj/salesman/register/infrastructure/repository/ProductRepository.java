package br.com.kproj.salesman.register.infrastructure.repository;

import br.com.kproj.salesman.register.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProductRepository<T extends Product, ID extends Serializable> extends JpaRepository<T, ID> {

}
