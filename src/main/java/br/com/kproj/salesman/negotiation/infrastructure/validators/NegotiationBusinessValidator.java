package br.com.kproj.salesman.negotiation.infrastructure.validators;


import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationValidate;
import org.springframework.stereotype.Component;

@Component
public class NegotiationBusinessValidator implements NegotiationValidate {

    @Override
    public void checkRules(Negotiation negotiation) {

    }
}
