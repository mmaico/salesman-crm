package br.com.kproj.salesman.infrastructure.entity;


import br.com.kproj.salesman.infrastructure.entity.enums.SaleTemperature;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class Product extends Identifiable {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    protected SaleTemperature temperature;

    public SaleTemperature getTemperature() {
        return temperature;
    }

    public Product(){}
    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(getId());
        sb.append(", temperature=").append(temperature);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
