package br.com.kproj.salesman.register.view.users;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverEntity;
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
    public  @ResponseBody void save(@ModelAttribute ApproverEntity approverEntity, @PathVariable Long userId) {

        UserEntity user = UserEntityBuilder.createUser(userId).build();
        approverEntity.setApprover(user);
        approverEntity.getFields().add("available");

        application.register(approverEntity);

    }




}
