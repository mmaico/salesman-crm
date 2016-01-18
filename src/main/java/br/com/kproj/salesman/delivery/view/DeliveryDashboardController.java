package br.com.kproj.salesman.delivery.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DeliveryDashboardController {

    @RequestMapping(value="/delivery/dashboard")
    public ModelAndView showDashboard() {

        return new ModelAndView("/delivery/delivery-dashboard");
    }

}
