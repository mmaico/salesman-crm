package br.com.kproj.salesman.delivery.tasks.view;


import org.springframework.web.bind.annotation.RestController;


@RestController("subTaskEndpointDeliveryTaskModule")
public class SubtaskEndpoint {

//    private SubTaskFacade service;
//
//    private SubtaskResourceBuilder builder;
//
//    private HttpServletRequest request;
//
//    @Autowired
//    public SubtaskEndpoint(SubtaskResourceBuilder builder, SubTaskFacade service, HttpServletRequest request) {
//        this.builder = builder;
//        this.service = service;
//        this.request = request;
//    }
//
//    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/{roottaskId}/subtask-definitions", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItems getSubtasks(@PathVariable Long roottaskId) {
//        RootTask rootTask = new RootTask(roottaskId);
//
//        Collection<Subtask> result = service.findAll(rootTask);
//
//        if(Iterables.isEmpty(result)) {
//            throw new NotFoundException();
//        }
//
//        return builder.build(result, request.getRequestURI());
//    }
//
//    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/subtask-definitions/{subtaskId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItem findOne(@PathVariable Long subtaskId) {
//
//        Optional<Subtask> subtask = service.getOne(subtaskId);
//
//        Subtask task = subtask.orElseThrow(() -> new NotFoundException());
//
//        return builder.build(task, request.getRequestURI());
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/{roottaskId}/subtask-definitions", method = RequestMethod.POST)
//    public @ResponseBody
//    ResourceItem create(@PathVariable Long roottaskId, @RequestBody SubTaskResource resource) {
//
//        Subtask subtask = createSubtask(resource.getId()).build();
//
//        SubtaskToRootTask toRootTask = SubtaskToRootTask.createSubtask(roottaskId, subtask);
//
//        Optional<Subtask> subtaskCreated = service.register(toRootTask);
//
//        Subtask task = subtaskCreated.orElseThrow(() -> new NotFoundException());
//
//        return builder.build(task, request.getRequestURI());
//    }
//


}
