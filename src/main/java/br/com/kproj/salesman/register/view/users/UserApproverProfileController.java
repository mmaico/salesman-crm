package br.com.kproj.salesman.register.view.users;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import br.com.kproj.salesman.register.application.contract.UserApproverProfileApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApproverProfileController {

    @Autowired
    private UserApproverProfileApplication application;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;



    @RequestMapping(value = "/users/{userId}/approver-profile/save", method = RequestMethod.POST)
    public  @ResponseBody void save(@ModelAttribute ApproverProfile approverProfile, @PathVariable Long userId) {

        User user = UserBuilder.createUser(userId).build();
        approverProfile.setApprover(user);
        approverProfile.getFields().add("available");

        application.register(approverProfile);

    }




}
