package br.com.kproj.salesman.register.view.saleable;

import org.springframework.web.bind.annotation.*;


@RestController(value = "oldServiceController")
public class ServiceController {

//    @Autowired
//    private ServiceSaleableApplication service;
//
//    @Autowired
//    private SaleableValidator validator;
//
//    @Autowired
//    private NormalizeEntityRequest normalizeEntityRequest;
//
//    @InitBinder(value = {"service"})
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//
//
//    }
//
//    @RequestMapping(value = "/services/save", method = RequestMethod.POST)
//    public @ResponseBody
//    SaleableUnitEntity save(@ModelAttribute @Validated ServiceEntity service, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(bindingResult.getAllErrors());
//        }
//
//        SaleableUnitEntity saleable = this.service.register(service);
//
//        return saleable;
//    }
//
//    @RequestMapping(value = "/services/save", method = RequestMethod.PUT)
//    public @ResponseBody void update(@ModelAttribute ServiceEntity service, BindingResult bindingResult) {
//        normalizeEntityRequest.addFieldsToUpdate(service);
//        this.service.register(service);
//    }
//
//    @RequestMapping("/services/list")
//    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
//
//        Pager pager = Pager.binding(pageable);
//
//        Iterable<ServiceEntity> result = this.service.findAll(pager);
//
//        model.addAttribute("services", result);
//        return new ModelAndView("/saleables/services/serviceList");
//    }
//
//    @RequestMapping(value="/services/{serviceId}")
//    public ModelAndView viewInfo(@PathVariable Long serviceId, Model model) {
//
//        Optional<ServiceEntity> result = this.service.getOne(serviceId);
//
//        model.addAttribute("service", result.isPresent() ? result.get(): null);
//        return new ModelAndView("/saleables/services/serviceDetail");
//    }
//
//    @RequestMapping(value="/services/create")
//    public ModelAndView newProduct() {
//
//        return new ModelAndView("/saleables/services/serviceEdit");
//    }

}
