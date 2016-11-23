package br.com.kproj.salesman.accounts.customers.view;

import br.com.kproj.salesman.accounts.customers.application.AccountFacade;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerValidator;
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
public class AccountsController {

    @Autowired
    private AccountFacade service;

    @Autowired
    @Qualifier("accountViewValidator")
    private CustomerValidator validator;

    @Autowired
    private NormalizeAttrUpdateHelper attributesToUpdate;


    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public @ResponseBody
    Customer save(@ModelAttribute Customer account) {

        validator.checkRules(account);

        Optional<Customer> accountSaved = service.register(account);

        return accountSaved.isPresent() ? accountSaved.get() : null;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute Customer account) {

        attributesToUpdate.addAttributesToUpdate(account);
        service.register(account);
    }

    @RequestMapping("/accounts/list")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Customer> result = this.service.findAll(pager);

        model.addAttribute("accounts", result);
        return new ModelAndView("/saleables/products/productList");
    }
    
    @RequestMapping(value="/accounts/{accountId}")
    public ModelAndView viewInfo(@PathVariable Long accountId, Model model) {
        
        Optional<Customer> result = this.service.getOne(accountId);

        model.addAttribute("account", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/products/productDetail");
    }


    @RequestMapping(value="/accounts/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/products/productEdit");
    }

}
