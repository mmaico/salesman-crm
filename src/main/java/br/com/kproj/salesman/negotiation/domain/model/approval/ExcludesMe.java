package br.com.kproj.salesman.negotiation.domain.model.approval;

import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;


public class ExcludesMe implements ValueObject {

    private final Seller seller;

    public ExcludesMe (Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public static ExcludesMe build(Seller seller) {
        return new ExcludesMe(seller);
    }

}
