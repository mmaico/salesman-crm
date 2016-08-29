package br.com.kproj.salesman.negotiationold.proposal.view.dto;


public class BusinessProposalRequestMergeHelper {







//    public static BusinessProposalEntity merge(ProposalSaleablesDTO dto, BusinessProposalEntity proposal) {
//
//        List<ProposalSaleableItem> saleableItems = Lists.newArrayList();
//        SaleableApplication saleableApplication = ServiceLocator.getBean(SaleableApplication.class);
//        SalePackageApplication packageApplication = ServiceLocator.getBean(SalePackageApplication.class);
//
//        for (ProposalSaleableItemDTO item : dto.getProposalSaleableItemDTOs()) {
//
//            if (item.getPackageItems().isEmpty()) {
//                ProposalSaleableItem proposalSaleableItem = ProposalSaleableItemBuilder.createProposalSaleable(item.getId())
//                        .withPrice(item.getPrice())
//                        .withQuantity(item.getQuantity())
//                        .withOriginalPrice(saleableApplication.getOne(item.getSaleableId()).get().getPrice())
//                        .withSaleable(new SaleableUnitEntity(item.getSaleableId())).build();
//
//                saleableItems.add(proposalSaleableItem);
//            } else {
//                Optional<SalePackageEntity> salePackage = packageApplication.getOne(item.getSaleableId());
//
//                //add package
//                saleableItems.add(ProposalSaleableItemBuilder.createProposalSaleable(item.getId())
//                        .withPackage(new SalePackageEntity(item.getSaleableId()))
//                        .withPrice(item.getPrice())
//                        .withQuantity(item.getQuantity())
//                        .withOriginalPrice(salePackage.get().calcPriceByProducts() ? BigDecimal.ZERO : salePackage.get().getPrice())
//                        .build()
//                );
//                 //add items package
//
//                item.getPackageItems().stream().filter(subItem -> subItem.isSelected()).forEach(subitems ->
//
//                       saleableItems.add(ProposalSaleableItemBuilder.createProposalSaleable(subitems.getId())
//                            .withPackage(new SalePackageEntity(item.getSaleableId()))
//                            .withPrice(subitems.getPrice())
//                            .withOriginalPrice(saleableApplication.getOne(item.getSaleableId()).get().getPrice())
//                            .withQuantity(subitems.getQuantity())
//                            .withSaleable(new SaleableUnitEntity(subitems.getSaleableId())).build()
//                       )
//                );
//            }
//
//        }
//
//        proposal.setSaleableItems(saleableItems);
//
//        return proposal;
//    }


}
