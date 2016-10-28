package br.com.kproj.salesman.products_catalog.infrastructure.persistence

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.products_catalog.domain.model.products.Product
import org.springframework.beans.factory.annotation.Autowired

class ProductRepositoryHibernateIT extends AbstractIntegrationTest {

    @Autowired
    def ProductRepositoryHibernate repository;

    def "should save a product with saleable created"() {
        def product = new Product()
        product.setId(6l)

        def productSaved = repository.save(product)

        expect:
            productSaved.get().getId() != null
    }
}
