package br.com.kproj.salesman.register.view.users;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.BranchRepository;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.repository.UserPositionRepository;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import br.com.kproj.salesman.register.application.contract.UserApproverProfileApplication;
import br.com.kproj.salesman.register.infrastructure.validators.UserValidator;
import br.com.kproj.salesman.register.view.dto.UserVO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
