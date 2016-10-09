package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Persistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public abstract class Identifiable implements Persistable<Long>, Serializable {

    @Transient
    @ExcludeField
    private Set<String> fields = new HashSet<>();

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
        return ReflectionToStringBuilder.toString(this);
    }

    public void addFields(String fieldName) {
        fields.add(fieldName);
    }

    public Set<String> getFields() {
        return this.fields;
    }
}
