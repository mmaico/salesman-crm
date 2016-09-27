package br.com.kproj.salesman.notifications.delivery.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryNotificationEndpoint {




    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public @ResponseBody void deliveryNotification(Model model) {


    }


}
