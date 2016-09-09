package br.com.kproj.salesman.register.application.prepare;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.repository.ProposalSaleableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("productsPreUpdate")
public class PreUpdateProductsImpl implements PreUpdateItems {

    @Autowired
    private BusinessProposalRepository repository;

    @Autowired
    private ProposalSaleableRepository saleableRepository;

    private enum Operation {
        INTERSECTION, LEFT_JOIN, RIGHT_JOIN
    }

//    @Autowired
//    private NegotiationApplication application;

    private Map<Operation, Join> joinMap = new HashMap<>();
    {
        joinMap.put(Operation.INTERSECTION, (pLeft, pRight) -> pLeft.
                getSaleableItems().stream().filter(leftItem -> pRight.getSaleableItems().contains(leftItem)).collect(Collectors.toList()));

        joinMap.put(Operation.LEFT_JOIN, (pLeft, pRight) -> pLeft.
                getSaleableItems().stream().filter(pLeftItem -> !pRight.getSaleableItems().contains(pLeftItem)).collect(Collectors.toList()));

        joinMap.put(Operation.RIGHT_JOIN, (pRight, pLeft) -> pRight.
                getSaleableItems().stream().filter(pRightItem -> !pLeft.getSaleableItems().contains(pRightItem)).collect(Collectors.toList()));
    }

    @Override
    public void preUpdate(BusinessProposalEntity proposalNew) {

//        if (proposalNew.isNew()) return;
//
//        BusinessProposalEntity proposalLoaded = application.getOne(proposalNew.getId()).get();
//
//        List<ProposalSaleableItem> intersections = joinMap.get(Operation.INTERSECTION).join(proposalNew, proposalLoaded);
//        intersections.forEach(item -> proposalLoaded.updateSaleableItem(item));
//
//        List<ProposalSaleableItem> leftJoin = joinMap.get(Operation.LEFT_JOIN).join(proposalLoaded, proposalNew);
//        leftJoin.forEach(item -> proposalLoaded.getSaleableItems().remove(item));
//        leftJoin.forEach(itemToDelete -> saleableRepository.delete(itemToDelete));
//
//        List<ProposalSaleableItem> rightJoin = joinMap.get(Operation.RIGHT_JOIN).join(proposalNew, proposalLoaded);
//        rightJoin.forEach(item -> proposalLoaded.addNewProposalSaleableItem(item));
//
//        repository.save(proposalLoaded);
    }

    private interface Join {

        List<ProposalSaleableItem> join(BusinessProposalEntity proposalOld, BusinessProposalEntity proposalNew);
    }

}
