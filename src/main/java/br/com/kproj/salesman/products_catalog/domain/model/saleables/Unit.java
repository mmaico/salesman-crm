package br.com.kproj.salesman.products_catalog.domain.model.saleables;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import lombok.Data;

import javax.persistence.Id;

@Data
public class Unit extends Identifiable {

    private Long id;
    private String name;


}
