package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.LocationHelper;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.infrastructure.validators.ClientVOValidator;
import br.com.kproj.salesman.register.view.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientVOValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private LocationHelper locationHelper;

    @InitBinder(value = "clientDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }



    @RequestMapping(value = "/clients/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity save(@ModelAttribute @Validated ClientDTO clientDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.doNestedReference(clientDTO.getClient());
        Client clientSaved = service.register(clientDTO.getClient());

        model.addAttribute("client", clientSaved);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/save", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity update(@ModelAttribute @Validated ClientDTO clientDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(clientDTO.getClient());
        Client clientSaved = service.register(clientDTO.getClient());

        model.addAttribute("client", clientSaved);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/clients/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Person> result = this.service.findAll(pager);

        model.addAttribute("clients", result);
        return new ModelAndView("/clients/list");
    }

    @RequestMapping(value="/clients/create")
    public ModelAndView newClient(Model model) {

        model.addAttribute("countries", locationHelper.getAllCountries());
        return new ModelAndView("/clients/newClient");
    }
    
    @RequestMapping(value="/clients/{clientId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="edit",required=false, value="template") String templateName,
                                 @PathVariable Long clientId, Model model) {
        
        Optional<Person> result = this.service.getOne(clientId);

        model.addAttribute("countries", locationHelper.getAllCountries());
        model.addAttribute("client", result.isPresent() ? result.get(): null);
        return new ModelAndView("/clients/" + templateName);
    }
}
