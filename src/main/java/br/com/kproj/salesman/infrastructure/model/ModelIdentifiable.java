package br.com.kproj.salesman.infrastructure.model;

import br.com.kproj.salesman.auditing.infrastructure.ExcludeAuditingField;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public abstract class ModelIdentifiable implements Serializable {

	private static final long serialVersionUID = 8213025865912695435L;


    @Transient
    @ExcludeAuditingField
    private Set<String> fields = new HashSet<>();

    public abstract Long getId();


    public boolean isNew() {
        return this.getId() == null;
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
