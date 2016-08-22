package br.com.kproj.salesman.administration.users.domain.service;


import br.com.kproj.salesman.administration.users.domain.model.user.User;

public interface UserEventService {

    void userChanged(User user);
}
