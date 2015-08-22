package br.com.kproj.salesman.infrastructure.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public abstract class Identifiable implements Persistable<Long> {

	private static final long serialVersionUID = 8213025865912695435L;


    @Transient
    private Set<String> fields = new HashSet<String>();


    @Override
    public abstract Long getId();


    @Override
    public boolean isNew() {
        return this.getId() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifiable)) return false;
        Identifiable that = (Identifiable) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Identifiable{");
        sb.append("id=").append(getId());
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
