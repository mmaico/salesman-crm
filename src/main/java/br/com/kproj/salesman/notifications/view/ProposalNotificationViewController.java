package br.com.kproj.salesman.notifications.view;

import br.com.kproj.salesman.infrastructure.entity.notification.ProposalNotificationView;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.notifications.application.ProposalNotificationViewApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;

@RestController
public class ProposalNotificationViewController {

    @Autowired
    private ProposalNotificationViewApplication application;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/notifications/proposals", method = RequestMethod.PUT)
    public @ResponseBody void save() {
        ProposalNotificationView userView = new ProposalNotificationView();
        userView.setUser(security.getPrincipal().getUser());

        application.saveUserView(userView);
    }



}
