package br.com.kproj.salesman.administration.approval_negotiation.application.validates;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverValidator;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.user.User;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.administration.approval_negotiation.application.validates.description.ApproverRulesDescription.ruleNotExists;
import static br.com.kproj.salesman.administration.approval_negotiation.application.validates.description.ApproverRulesDescription.ruleUserNotExists;

@Component
public class ApproverBusinessRules implements ApproverValidator {
    
    @Autowired
    private ApproverRepository repository;

    @Autowired
    private UserRepository userRepository;
    

    private Map<RuleKey, CheckRule<Approver>> persistRules = new HashMap<>();
    {
        persistRules.put(ruleNotExists(), (approver) ->
            approver.isNew() || !repository.findOne(approver.getId()).isPresent());

        persistRules.put(ruleUserNotExists(), (approver) -> {
                    User user = approver.getUser();
                    return user == null || user.isNew() || userRepository.findOne(user.getId()).isPresent();
                });

    }

    @Override
    public void exists(Approver approver) {
        Boolean check = persistRules.get(ruleNotExists()).check(approver);

        if (check) {
            throw new ValidationException("approver.not.exists");
        }

    }

    @Override
    public void isValid(Approver approver) {
        Boolean check = persistRules.get(ruleUserNotExists()).check(approver);

        if (check) {
            throw new ValidationException("approver.invalid.user");
        }
    }
}
