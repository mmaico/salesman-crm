package br.com.kproj.salesman.negotiation.negotiation.domain.model.seller;



import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;

public class SellerSaveNegotiation implements ValueObject {

    private final Seller seller;
    private final Negotiation negotiation;

    public SellerSaveNegotiation(Seller seller, Negotiation negotiation) {
        this.seller = seller;
        this.negotiation = negotiation;
    }

    public Seller getSeller() {
        return seller;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public static SellerSaveNegotiation createNegotiation(Seller seller, Negotiation negotiation) {
        return new SellerSaveNegotiation(seller, negotiation);
    }
}
