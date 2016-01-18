package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.sales.application.SalesOrderApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class DeliverySalesTasksController {

    @Autowired
    private SalesOrderApplication application;

    @RequestMapping(value="/delivery/sales-order/{salesId}")
    public ModelAndView showDetail(@PathVariable Long salesId, Model model) {
        Optional<SalesOrder> result = application.getOne(salesId);

        model.addAttribute("salesorder", result.isPresent() ? result.get() : null);
        return new ModelAndView("/delivery/sales-order-tasks");
    }

}
