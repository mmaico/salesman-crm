package br.com.kproj.salesman.sales.view;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.sales.application.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class SalesOrderController {

    @Autowired
    private SalesOrderService service;


    @RequestMapping(value="/sales-order/{orderId}")
    public ModelAndView viewInfo(@PathVariable Long orderId, Model model) {

        Optional<SalesOrder> result = this.service.getOne(orderId);

        model.addAttribute("user", result.isPresent() ? result.get() : null);
        return new ModelAndView("/users/edit");
    }
}
