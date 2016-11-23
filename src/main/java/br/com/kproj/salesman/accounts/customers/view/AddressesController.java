package br.com.kproj.salesman.accounts.customers.view;

import br.com.kproj.salesman.accounts.customers.application.AddressFacade;
import br.com.kproj.salesman.accounts.customers.domain.model.address.Address;
import br.com.kproj.salesman.accounts.customers.domain.model.address.AddressValidator;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeAttrUpdateHelper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
public class AddressesController {

    @Autowired
    private AddressFacade service;

    @Autowired
    @Qualifier("addressViewValidator")
    private AddressValidator validator;

    @Autowired
    private NormalizeAttrUpdateHelper attributesToUpdate;


    @RequestMapping(value = "/accounts/addresses", method = RequestMethod.POST)
    public @ResponseBody Address save(@ModelAttribute Address address) {

        validator.checkRules(address);

        Optional<Address> addressSaved = service.register(address);

        return addressSaved.isPresent() ? addressSaved.get() : null;
    }

    @RequestMapping(value = "/accounts/addresses", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute Address address) {

        attributesToUpdate.addAttributesToUpdate(address);
        service.register(address);
    }

    @RequestMapping("/accounts/addresses")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Address> result = this.service.findAll(pager);

        model.addAttribute("addresses", result);
        return new ModelAndView("/saleables/products/productList");
    }
    
    @RequestMapping(value="/accounts/addresses/{addressId}")
    public ModelAndView viewInfo(@PathVariable Long addressId, Model model) {
        
        Optional<Address> result = this.service.getOne(addressId);

        model.addAttribute("address", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/products/productDetail");
    }


    @RequestMapping(value="/accounts/addresses/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/products/productEdit");
    }

}
