package br.com.kproj.salesman.infrastructure.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="operation_region")
public class OperationRegionEntity extends Identifiable {


    @Id
    @GeneratedValue
    @Expose
    private Long id;

    @Expose
    private String name;

    public OperationRegionEntity() {}

    public OperationRegionEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
