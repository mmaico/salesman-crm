package br.com.kproj.salesman.infrastructure.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public class Identifiable implements Persistable<Long> {

    @Id
    @GeneratedValue
    @Column(name="id")
    protected final Long id;

    @Transient
    private Set<String> fields = new HashSet<String>();

    public Identifiable() {
        id = null;
    }

    public Identifiable(final Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifiable)) return false;
        Identifiable that = (Identifiable) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Identifiable{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public void addFields(String fieldName) {
        fields.add(fieldName);
    }

    public Set<String> getFields() {
        return this.fields;
    }
}
