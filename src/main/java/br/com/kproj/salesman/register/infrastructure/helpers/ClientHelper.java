package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.person.Company;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import org.springframework.stereotype.Component;

@Component
public class ClientHelper {

    public Boolean isCompany(Person person) {

        return (person instanceof Company) ? Boolean.TRUE : Boolean.FALSE;
    }
}
