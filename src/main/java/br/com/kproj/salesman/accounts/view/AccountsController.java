package br.com.kproj.salesman.accounts.view;

import br.com.kproj.salesman.accounts.application.AccountFacade;
import br.com.kproj.salesman.accounts.domain.model.account.Account;
import br.com.kproj.salesman.accounts.domain.model.account.AccountValidator;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeAttrUpdateHelper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
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
    private AccountValidator validator;

    @Autowired
    private NormalizeAttrUpdateHelper attributesToUpdate;


    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public @ResponseBody Account save(@ModelAttribute Account account) {

        validator.checkRules(account);

        Optional<Account> accountSaved = service.register(account);

        return accountSaved.isPresent() ? accountSaved.get() : null;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute Account account) {

        attributesToUpdate.addAttributesToUpdate(account);
        service.register(account);
    }

    @RequestMapping("/accounts/list")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Account> result = this.service.findAll(pager);

        model.addAttribute("accounts", result);
        return new ModelAndView("/saleables/products/productList");
    }
    
    @RequestMapping(value="/accounts/{accountId}")
    public ModelAndView viewInfo(@PathVariable Long accountId, Model model) {
        
        Optional<Account> result = this.service.getOne(accountId);

        model.addAttribute("account", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/products/productDetail");
    }


    @RequestMapping(value="/accounts/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/products/productEdit");
    }

}
