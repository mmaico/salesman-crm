package br.com.kproj.salesman.business_prospecting.campaigns.domain;

import br.com.kproj.salesman.business_prospecting.campaigns.infrastructure.CampaignsValidator;
import br.com.kproj.salesman.infrastructure.entity.campaigns.Campaign;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static br.com.kproj.salesman.infrastructure.validators.ModelValidatorUtils.hasId;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class CampaignsDomainServiceImpl implements CampaignsDomainService {

    @Autowired
    private CampaignsValidator validator;

    @Autowired
    private UserEntityRepository userEntityRepository;

    Map<String, CheckRuleLegacy<Campaign>> persistRules = new HashMap<>();
    {
        persistRules.put(description("campaign.base.validators"), (incident) -> hasContraintViolated(incident, validator));

        persistRules.put(description("campaign.with.invalid.createdby"), (campaign) ->
                campaign == null || !hasId(campaign.getCreatedBy()) || !userEntityRepository.exists(campaign.getCreatedBy().getId()));

    }

    @Override
    public void checkBusinessRulesFor(Campaign campaign) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(campaign))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
