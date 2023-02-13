package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.seller;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.services.GenerateSaleableItemsService;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.services.SaleableItemToNegotiatedService;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Model
public class Seller extends ModelIdentifiable {

    private Long id;

    @Autowired
    private NegotiatedRepository repository;

    @Autowired
    private SaleableItemRepository saleableItemRepository;

    public Seller() {
        AutowireHelper.autowire(this);
    }

    public GenerateSaleableItemsService save(Negotiated negotiated) {
        return (saleableItems ->
                (negotiation -> {

                    negotiated.setNegotiation(negotiation);
                    Optional<Negotiated> negotiatedSaved = repository.save(negotiated);
                    List<SaleableItem> items = negotiatedSaved.get().generate(saleableItems);
                    negotiatedSaved.get().setSaleableItems(items);

                    return negotiatedSaved;
                })
        );
    }

    public SaleableItemToNegotiatedService save(SaleableItem saleableItem) {
        return negotiated -> {
            saleableItem.setNegotiated(negotiated);
            return saleableItemRepository.save(saleableItem).get();
        };
    }

    public Negotiated update(Negotiated negotiated) {
        return repository.update(negotiated);
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public static Seller seller() {
        return new Seller();
    }
}
