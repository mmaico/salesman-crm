package br.com.kproj.salesman.register.view.providers;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.register.application.contract.AddressApplication;
import br.com.kproj.salesman.register.infrastructure.validators.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.ClientBuilder.createClient;
import static br.com.kproj.salesman.infrastructure.entity.builders.ProviderBuilder.createProvider;

@RestController
public class ProviderAddressController {

    @Autowired
    private AddressApplication service;

    @Autowired
    private AddressValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "address")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/providers/{providerId}/addresses/save", method = {RequestMethod.POST, RequestMethod.PUT})
    public  @ResponseBody void clientAddress(@ModelAttribute @Validated Address address,
                                             @PathVariable Long providerId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(address);
        normalizeEntityRequest.addFieldsToUpdate(address);
        service.register(address, createProvider(providerId).build());
    }


    @RequestMapping(value = "/providers/{providerId}/addresses", method = RequestMethod.GET)
    public ModelAndView getAddress(@PathVariable Long providerId, Model model) {

        List<Address> result = service.getAddressesByProvider(createProvider(providerId).build());

        model.addAttribute("addressItems", result);
        model.addAttribute("provider", createProvider(providerId).build());
        return new ModelAndView("providers/edit-address");
    }

}
