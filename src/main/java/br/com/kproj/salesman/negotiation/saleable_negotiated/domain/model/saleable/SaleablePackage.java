package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable;

import com.trex.shared.annotations.Model;

import java.util.List;

@Model
public class SaleablePackage extends Saleable {

    private List<Saleable> saleables;

    public SaleablePackage(){
        super();
    }
    public SaleablePackage(Long id){
        super(id);
    }


    public List<Saleable> getSaleables() {
        return saleables;
    }

    public void setSaleables(List<Saleable> saleables) {
        this.saleables = saleables;
    }
}
