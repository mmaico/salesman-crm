package br.com.kproj.salesman.infrastructure.model;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import com.trex.shared.annotations.UpdateAttributes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public abstract class ModelIdentifiable implements Serializable {

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

    public void addField(String name, When when) {
        if (when.present) {
            this.fields.add(name);
        }
    }

    public Boolean hasField(String fieldName) {
        return this.fields.contains(fieldName);
    }

    public Boolean needPersist(String fieldName) {
        if (this.getId() == null || StringUtils.isBlank(fieldName)) {
            return Boolean.TRUE;
        }
        return this.fields.contains(fieldName);
    }


    public static class When {
        final Boolean present;

        public When(Boolean value) {
            this.present = value;
        }

        public static When when(Boolean value) {
            return new When(value);
        }
    }
}
