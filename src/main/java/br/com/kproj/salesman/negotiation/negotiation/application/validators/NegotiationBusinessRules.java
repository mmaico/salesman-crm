package br.com.kproj.salesman.negotiation.negotiation.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.approval.ApprovalProcessRepository;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationValidate;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.negotiation.negotiation.application.validators.NegotiationIgnoreRules.*;


@Component
public class NegotiationBusinessRules implements NegotiationValidate {


    private CustomerRepository customerRepository;
    private SellerRepository sellerRepository;
    private ApprovalProcessRepository approvalRepository;


    @Autowired
    public NegotiationBusinessRules(CustomerRepository customerRepository, SellerRepository sellerRepository, ApprovalProcessRepository approvalRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.approvalRepository = approvalRepository;
    }

    private Map<String, CheckRule<Negotiation>> persistRules = new HashMap<>();
    {
        persistRules.put(ruleCustomer(), (negotiation) -> {
            Customer customer = negotiation.getCustomer();
            return customer.isNew() || !customerRepository.findOne(customer.getId()).isPresent();
        });

        persistRules.put(ruleSeller(), (negotiation) -> {
            Seller seller = negotiation.getSeller();
            return seller != null && !seller.isNew() && !sellerRepository.findOne(seller.getId()).isPresent();
        });

        persistRules.put(ruleForecast(), (negotiation) -> {
            Date deliveryForeCast = negotiation.getDeliveryForeCast();
            return deliveryForeCast != null && deliveryForeCast.before(new Date());
        });

        persistRules.put(ruleAmmountPayable(), (negotiation) -> {
            BigDecimal ammountPayable = negotiation.getAmmountPayable();
            return ammountPayable != null && ammountPayable.compareTo(BigDecimal.ZERO) < 0;
        });

        persistRules.put(ruleApprovalProcess(), (negotiation) ->
            approvalRepository.isInApprovalProcess(negotiation)
        );
    }

    @Override
    public void checkRules(Negotiation negotiation) {

        Set<String> errors = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(negotiation))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(errors).throwing(ValidationException.class);
    }
}
