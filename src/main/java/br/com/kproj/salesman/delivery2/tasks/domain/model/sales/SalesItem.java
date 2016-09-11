package br.com.kproj.salesman.delivery2.tasks.domain.model.sales;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class SalesItem extends ModelIdentifiable {

    private Long id;

    private Product product;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
