package br.com.kproj.salesman.register.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Client implements Accessor {

    @Id
    @GeneratedValue
    private final Long id;

    @NotNull
    private final String name;

    @OneToOne
    private final User user;

    public Client() {
        id = null;
        name = null;
        user = null;
    }

    public Client(Long id) {
        this.id = id;
        name = null;
        user = null;
    }

    public Client(String name, User user) {
        id = null;
        this.name = name;
        this.user = user;
    }

    @Deprecated
    public static void validate(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client is required");
        }
        if (client.getName() == null || "".equals(client.getName())) {
            throw new IllegalArgumentException("Client name is required");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
