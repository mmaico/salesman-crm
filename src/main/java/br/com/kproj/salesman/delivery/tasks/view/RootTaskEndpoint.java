package br.com.kproj.salesman.delivery.tasks.view;


import org.springframework.web.bind.annotation.RestController;


@RestController("rootTaskEndpointDeliveryTaskModule")
public class RootTaskEndpoint {

//    private RootTaskFacade service;
//
//    private RootTaskResourceBuilder builder;
//
//    private HttpServletRequest request;
//
//    @Autowired
//    public RootTaskEndpoint (RootTaskResourceBuilder builder, RootTaskFacade service, HttpServletRequest request) {
//        this.builder = builder;
//        this.service = service;
//        this.request = request;
//    }
//
//    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItems getRootTasksBy() {
//
//        Page<RootTask> result = service.findAll(Pager.build().withPageSize(10000));
//
//        if(Iterables.isEmpty(result)) {
//            throw new NotFoundException();
//        }
//
//        return builder.build(result.getContent(), request.getRequestURI());
//    }
//
//    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/{rootTaskId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItem findOne(@PathVariable Long rootTaskId) {
//
//        Optional<RootTask> rootTask = service.getOne(rootTaskId);
//
//        RootTask task = rootTask.orElseThrow(() -> new NotFoundException());
//
//        return builder.build(task, request.getRequestURI());
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/rs/saleables/task-definitions/{taskDefinitionId}/root-task-definitions", method = RequestMethod.POST)
//    public @ResponseBody
//    ResourceItem create(@PathVariable Long taskDefinitionId) {
//
//        RootTask rootTask = RootTaskBuilder.createRootTask(taskDefinitionId).build();
//
//        Optional<RootTask> rootTaskCreated = service.register(rootTask);
//
//        return builder.build(rootTaskCreated.get(), request.getRequestURI());
//    }

}
