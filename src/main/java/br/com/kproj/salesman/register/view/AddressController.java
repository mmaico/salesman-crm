package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.register.application.AddressService;
import br.com.kproj.salesman.register.infrastructure.validators.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static br.com.kproj.salesman.infrastructure.entity.builders.ClientBuilder.createClient;
import static br.com.kproj.salesman.infrastructure.entity.builders.ProviderBuilder.createProvider;

@RestController
public class AddressController {

    @Autowired
    private AddressService service;

    @Autowired
    private AddressValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "address")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/clients/{clientId}/addresses/save", method = {RequestMethod.PUT, RequestMethod.POST})
    public @ResponseBody void update(@ModelAttribute @Validated Address address, @PathVariable("clientId") Long clientId,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(address);
        service.register(createClient(clientId).build(), address);        
    }

    @RequestMapping(value = "/providers/{providerId}/addresses/save", method = {RequestMethod.PUT, RequestMethod.POST})
    public @ResponseBody void updateAddressProvider(@ModelAttribute @Validated Address address, @PathVariable("providerId") Long providerId,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(address);
        service.register(createProvider(providerId).build(), address);
    }

}
