package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.infraestructure.validators.ClientVOValidator;
import br.com.kproj.salesman.register.view.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController(value = "/")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientVOValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "clientDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping("clients/save")
    public ModelAndView save(@ModelAttribute @Validated  ClientDTO clientDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.doNestedReference(clientDTO);
        Client clientSaved = service.register(clientDTO.getClient());

        model.addAttribute("client", clientSaved);

        return new ModelAndView("client");
    }

    @RequestMapping(value="clients/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Client> result = this.service.findAll(pager);

        model.addAttribute("clients", result);
        return new ModelAndView("client");
    }
}
