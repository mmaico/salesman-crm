package br.com.kproj.salesman.infrastructure.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class Product extends AbstractEntity {


    @ManyToOne
    protected final Client client;

    protected final SalesTemperature temperature;

    public Product() {
        setId(null);
        client = null;
        temperature = null;
    }

    public Product(final Long id, final Client client, final SalesTemperature temperature) {
        this.setId(id);
        this.temperature = temperature;
        this.client = client;
    }

    public SalesTemperature getTemperature() {
        return temperature;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(getId());
        sb.append(", client=").append(client);
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
