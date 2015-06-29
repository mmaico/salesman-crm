package br.com.kproj.salesman.infrastructure.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public class AbstractEntity implements Persistable<Long> {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Transient
    private Set<String> fields = new HashSet<String>();


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    public void addFields(String fieldName) {
        fields.add(fieldName);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        AbstractEntity other = (AbstractEntity) obj;
        return new EqualsBuilder().append(this.id, other.id).isEquals();
    }

    public Set<String> getFields() {
        return this.fields;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
