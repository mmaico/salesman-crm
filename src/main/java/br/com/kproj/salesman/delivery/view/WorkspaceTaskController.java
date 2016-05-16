package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.WorkspaceApplication;
import br.com.kproj.salesman.infrastructure.entity.builders.WorkspaceUnitBuilder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WorkspaceTaskController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private WorkspaceApplication application;

    @RequestMapping(value="/delivery/workspace/list")
    public ModelAndView showMyWorkspace(Model model) {

        List<SalesOrder> salesToWork = application.findBy(security.getPrincipal().getUser());
        model.addAttribute("salesOrders", salesToWork);
        return new ModelAndView("/delivery/tasks/list");
    }

}
