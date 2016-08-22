package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellersHelper {

    @Autowired
    private UserApplication application;

    public Iterable<UserEntity> getAllSellers() {
        return application.findAll(Pager.build().withPageSize(1000));
    }
}
