package br.com.kproj.salesman.register.view.dto;


import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.entity.Company;
import br.com.kproj.salesman.infrastructure.entity.Individual;
import br.com.kproj.salesman.infrastructure.helpers.ReflectionsUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

public class ClientDTO extends Client {

    private static final String COMPANY = "company";
    private static final String INDIVIDUAL = "individual";

    private String type;


    private Individual individual = null;

    private Company company = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Client getClient() {
        if (COMPANY.equals(this.getType())) {
            ReflectionsUtils.copyProperties(this.company, this);
            return this.company;
        } else if (INDIVIDUAL.equals(this.getType())) {
            ReflectionsUtils.copyProperties(this.individual, this);
            return this.individual;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
