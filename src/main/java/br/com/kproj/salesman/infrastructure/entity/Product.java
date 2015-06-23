package br.com.kproj.salesman.infrastructure.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class Product {

    @Id
    @GeneratedValue
    protected final Long id;

    @ManyToOne
    protected final Client client;

    protected final SalesTemperature temperature;

    public Product() {
        id = null;
        client = null;
        temperature = null;
    }

    public Product(final Long id, final Client client, final SalesTemperature temperature) {
        this.id = id;
        this.temperature = temperature;
        this.client = client;
    }

    public Long getId() {
        return id;
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
        sb.append("id=").append(id);
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
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
