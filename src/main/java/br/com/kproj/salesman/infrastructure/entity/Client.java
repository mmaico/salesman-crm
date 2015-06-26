package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Client extends AbstractEntity implements Accessor {

    @NotNull @Min(2) @Max(30)
    private String name;

    @Size(max = 30)
    private String tradingName;


    @OneToOne
    private User user;

    public Client() {}
    public Client(String name, User user) {
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

    public String getName() {
        return name;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(getId());
        sb.append(", name='").append(name).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }

}
