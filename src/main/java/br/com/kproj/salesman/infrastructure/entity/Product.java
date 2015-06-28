package br.com.kproj.salesman.infrastructure.entity;


import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class Product extends Identifiable {


    @ManyToOne
    protected final Client client;

    protected final SaleTemperature temperature;

    public Product() {
        super();
        client = null;
        temperature = null;
    }

    public Product(final Long id, final Client client, final SaleTemperature temperature) {
        super(id);
        this.temperature = temperature;
        this.client = client;
    }

    public SaleTemperature getTemperature() {
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
