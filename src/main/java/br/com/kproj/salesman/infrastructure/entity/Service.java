package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Service extends Product {


    public Service(){}
    public Service(String name) {
        super(name);
    }


}
