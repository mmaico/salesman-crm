package br.com.kproj.salesman.business_prospecting.campaigns.application.validators;


import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.CampaignChange;
import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.CampaignValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class CampaignChangeBusinessRules implements CampaignValidator {


    private Map<String, CheckRule<CampaignChange>> rules = new HashMap<>();
    {
        rules.put("campaign.invalid.name", (campaignChange) -> isBlank(campaignChange.getCampaign().getName()));
        rules.put("campaign.with.invalid.author", (campaignChange) -> campaignChange.getCampaign().isNew()
                && (campaignChange.getCampaign().getAuthor() == null || campaignChange.getCampaign().getAuthor().isNew()));
    }

    @Override
    public void checkRules(CampaignChange change) {

        Set<String> errors = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(change))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(errors).throwing(ValidationException.class);
    }
}
