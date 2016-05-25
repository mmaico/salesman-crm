package br.com.kproj.salesman.auditing.view;

import br.com.kproj.salesman.auditing.application.BusinessProposalAuditingApplication;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BusinessProposalLogController {


    @Autowired
    private BusinessProposalAuditingApplication application;




    @RequestMapping(value = "/proposals/{proposalId}/logs", method = RequestMethod.GET)
    public ModelAndView getListLogs(@PathVariable Long proposalId,
                                    @PageableDefault(size = 1000) Pageable pageable, Model model) {

        Page<BusinessProposalAudinting> result = application.findLogs(proposalId, Pager.binding(pageable));

        model.addAttribute("domain", BusinessProposalBuilder.createBusinessProposal(proposalId).build());
        model.addAttribute("proposalLogs", result);
        return new ModelAndView("/clients/domain/logs/logs");

    }


}
