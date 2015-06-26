package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.register.application.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController(value = "/")
public class ClientController {

    @Autowired
    private RegisterService service;

    @RequestMapping("client/save")
    public ModelAndView save(@Valid @ModelAttribute Client client, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorsList", bindingResult.getAllErrors());
            return new ModelAndView("errors");
        }

        Client clientSaved = service.register(client);

        model.addAttribute(clientSaved);
        return new ModelAndView("client");
    }
}
