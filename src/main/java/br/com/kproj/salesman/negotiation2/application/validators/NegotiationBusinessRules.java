package br.com.kproj.salesman.negotiation2.application.validators;

import br.com.kproj.salesman.accounts.domain.model.account.AccountRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.NegotiationValidate;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.SaleableItemValidate;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Temperature;
import br.com.kproj.salesman.negotiation2.domain.model.payment.InstallmentValidate;
import br.com.kproj.salesman.negotiation2.domain.model.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class NegotiationBusinessRules implements NegotiationValidate {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SaleableItemValidate saleableItemValidate;

    @Autowired
    private InstallmentValidate installmentValidate;

    private Map<String, CheckRule<Negotiation>> persistRules = new HashMap<>();
    {
        persistRules.put(description("negotiation.verify.won.cannotbe.changed"), (n) -> Temperature.CLOSED_WON == n.getTemperature());

        persistRules.put(description("negotiation.verify.valid.account"), (n) -> !(!(n).getAccount().isNew()
                && accountRepository.findOne(n.getAccount().getId()).isPresent()));

        persistRules.put(description("negotiation.verify.valid.seller"), (n) -> !(!(n).getSeller().isNew() && sellerRepository.findOne(n.getSeller().getId()).isPresent()));
        persistRules.put(description("negotiation.verify.not.empty.products"), (n) -> isEmptySafe(n.getSaleablesItems()));
        persistRules.put(description("negotiation.verify.not.empty.installments"), (n) -> isEmptySafe(n.getInstallments()));
        persistRules.put(description("negotiation.verify.valid.saleable.items"), (n) -> !saleableItemValidate.checkRules(n.getSaleablesItems()));
        persistRules.put(description("negotiation.verify.valid.installment.items"), (n) -> !installmentValidate.checkRules(n.getInstallments()));
    }

    @Override
    public void checkRules(Negotiation negotiation) {

    }
}
