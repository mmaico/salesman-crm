package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {


    private final String login, password;

    public User() {
        setId(null);
        login = null;
        password = null;
    }

    public User(Long id) {
        setId(null);
        login = null;
        password = null;
    }

    public User(String login, String password) {
        setId(null);
        this.login = login;
        this.password = password;
    }



    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
