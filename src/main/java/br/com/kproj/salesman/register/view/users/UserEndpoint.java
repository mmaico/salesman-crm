package br.com.kproj.salesman.register.view.users;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserEndpoint {

    @Autowired
    private UserApplication service;



    @RequestMapping(value="/rs/users/{userId}")
    public @ResponseBody User viewInfo(@PathVariable Long userId, Model model) {
        
        Optional<User> result = this.service.getOne(userId);

        return result.isPresent() ? result.get() : null;
    }

}
