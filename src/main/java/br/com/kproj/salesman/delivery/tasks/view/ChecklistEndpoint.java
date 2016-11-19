package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.products_catalog.delivery_definition.application.ChecklistFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders.ChecklistResourceBuilder;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;


@RestController("checklistEndpointDeliveryTaskModule")
public class ChecklistEndpoint {

    private ChecklistFacade service;

    private ChecklistResourceBuilder builder;

    private HttpServletRequest request;

//    @Autowired
//    public ChecklistEndpoint(ChecklistFacade service, ChecklistResourceBuilder builder, HttpServletRequest request) {
//        this.service = service;
//        this.builder = builder;
//        this.request = request;
//    }
//
//    @RequestMapping(value = "/rs/saleables/task-definitions/{taskDefinitionId}/checklist-definitions", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItems listChecklists(@PathVariable Long taskDefinitionId) {
//        Task task = TaskBuilder.createTask(taskDefinitionId).build();
//
//        Collection<Checklist> checklists = service.findAll(task);
//
//        return builder.build(checklists, request.getRequestURI());
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/rs/saleables/task-definitions/{taskDefinitionId}/checklist-definitions", method = RequestMethod.POST)
//    public @ResponseBody
//    ResourceItem create(@PathVariable Long taskDefinitionId, @RequestBody ChecklistResource resource) {
//
//        Checklist checklist = ChecklistBuilder.createChecklist().withName(resource.getName()).build();
//
//        Optional<Checklist> checklistCreated = service.register(createChecklistToTask(taskDefinitionId, checklist));
//
//        return builder.build(checklistCreated.get(), request.getRequestURI());
//    }
//
//    @RequestMapping(value = "/rs/saleables/task-definitions/checklist-definitions/{checklistId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItem getOne(@PathVariable Long checklistId) {
//
//        Optional<Checklist> checklistFound = service.getOne(checklistId);
//
//        Checklist checklist = checklistFound.orElseThrow(() -> new NotFoundException());
//
//        return builder.build(checklist, request.getRequestURI());
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/rs/saleables/task-definitions/checklist-definitions/{checklistId}", method = RequestMethod.DELETE)
//    public @ResponseBody
//    void remove(@PathVariable Long checklistId) {
//        service.delete(ChecklistBuilder.createChecklist(checklistId).build());
//    }



}
