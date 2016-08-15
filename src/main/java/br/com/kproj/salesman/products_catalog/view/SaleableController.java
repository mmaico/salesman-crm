package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class SaleableController {

    @Autowired
    private SaleableApplication application;




    @RequestMapping("/saleables/list")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<SaleableUnitEntity> result = this.application.findAll(pager);

        model.addAttribute("saleables", result);
        return new ModelAndView("/saleables/saleablesList");
    }
    


}
