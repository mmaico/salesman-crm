package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.application.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "/")
public class ClientController {

    @Autowired
    private RegisterService service;

    @RequestMapping("client/save")
    public ModelAndView save(@Valid @ModelAttribute Client client, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        Client clientSaved = service.register(client);

        model.addAttribute(clientSaved);
        return new ModelAndView("client");
    }
}
