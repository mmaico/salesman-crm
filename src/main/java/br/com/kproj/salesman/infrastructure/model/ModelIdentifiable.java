package br.com.kproj.salesman.infrastructure.model;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import com.trex.shared.annotations.UpdateAttributes;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public abstract class ModelIdentifiable implements Serializable {

	private static final long serialVersionUID = 8213025865912695435L;


    @ExcludeField
    @UpdateAttributes
    private Set<String> fields = new HashSet<>();

    public abstract Long getId();


    public boolean isNew() {
        return this.getId() == null;
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
