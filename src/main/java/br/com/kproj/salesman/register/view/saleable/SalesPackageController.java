package br.com.kproj.salesman.register.view.saleable;

import org.springframework.web.bind.annotation.*;


@RestController(value = "oldSalesPackageController")
public class SalesPackageController {

//    @Autowired
//    private SalePackageApplication service;
//
//    @Autowired
//    private SalePackageValidator validator;
//
//    @Autowired
//    private NormalizeEntityRequest normalizeEntityRequest;
//
//    @InitBinder(value = {"salePackage"})
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//
//    }
//
//    @RequestMapping(value = "/sales-package/save", method = RequestMethod.POST)
//    public @ResponseBody
//    SalePackageEntity save(@ModelAttribute @Validated SalePackageEntity salePackage, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(bindingResult.getAllErrors());
//        }
//
//        SalePackageEntity salePackageResult = service.register(salePackage);
//
//        return salePackageResult;
//    }
//
//    @RequestMapping(value = "/sales-package/save", method = RequestMethod.PUT)
//    public @ResponseBody
//    void update(@ModelAttribute SalePackageEntity salePackage) {
//
//        normalizeEntityRequest.addFieldsToUpdate(salePackage);
//        service.register(salePackage);
//
//    }
//
//    @RequestMapping("/sales-package/list")
//    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
//
//        Pager pager = Pager.binding(pageable);
//
//        Iterable<SalePackageEntity> result = this.service.findAll(pager);
//
//        model.addAttribute("packages", result);
//        return new ModelAndView("/saleables/packages/packageList");
//    }
//
//    @RequestMapping(value="/sales-package/{packageId}")
//    public ModelAndView viewInfo(@PathVariable Long packageId, Model model) {
//
//        Optional<SalePackageEntity> result = this.service.getOne(packageId);
//
//        model.addAttribute("salesPackage", result.isPresent() ? result.get(): null);
//
//        return new ModelAndView("/saleables/packages/packageDetail");
//    }
//
//    @RequestMapping(value="/sales-package/{packageId}/add-saleable/{saleableId}", method = RequestMethod.PUT)
//    public @ResponseBody void addProductOrService(@PathVariable Long packageId, @PathVariable Long saleableId) {
//
//        SalePackageEntity salePackage = SalePackageBuilder.createPackage(packageId).build();
//        SaleableUnitEntity saleableUnit = SaleableUnitBuilder.createSaleableUnit(saleableId).build();
//
//        this.service.addProductOrService(salePackage, saleableUnit);
//    }
//
//    @RequestMapping(value="/sales-package/{packageId}/remove-saleable/{saleableId}", method = RequestMethod.DELETE)
//    public @ResponseBody void removeSaleable(@PathVariable Long packageId, @PathVariable Long saleableId) {
//
//        SalePackageEntity salePackage = SalePackageBuilder.createPackage(packageId).build();
//        SaleableUnitEntity saleableUnit = SaleableUnitBuilder.createSaleableUnit(saleableId).build();
//
//        this.service.removeProductOrService(salePackage, saleableUnit);
//    }
//
//    @RequestMapping(value="/sales-package/create")
//    public ModelAndView newProduct() {
//
//        return new ModelAndView("/saleables/packages/packageEdit");
//    }

}
