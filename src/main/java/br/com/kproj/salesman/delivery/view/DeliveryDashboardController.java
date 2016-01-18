package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.ActDeliverySalesApplication;
import br.com.kproj.salesman.infrastructure.entity.ActDeliverySales;
import br.com.kproj.salesman.infrastructure.entity.builders.ActDeliverySalesBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder.createSalesOrder;

@RestController
public class DeliveryDashboardController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private ActDeliverySalesApplication application;

    @RequestMapping(value="/delivery/dashboard")
    public ModelAndView showDashboard() {

        return new ModelAndView("/delivery/delivery-dashboard");
    }

    @RequestMapping(value="/delivery/act/proposal/{salesId}", method = RequestMethod.POST)
    public void act(@PathVariable("salesId") Long salesId) {

        ActDeliverySales actDeliverySales = ActDeliverySalesBuilder.createActDelivery()
                .withUser(security.getPrincipal().getUser())
                .withSalesOrder(createSalesOrder(salesId).build()).build();

        application.register(actDeliverySales);
    }
}
