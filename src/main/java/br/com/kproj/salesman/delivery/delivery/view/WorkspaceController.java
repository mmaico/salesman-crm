package br.com.kproj.salesman.delivery.delivery.view;


import br.com.kproj.salesman.delivery.delivery.application.DeliveryFacade;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkspaceController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private DeliveryFacade workspaceFacade;

//    @RequestMapping(value="/workspaces/{workspaceId}/workers/{workerId}", method = RequestMethod.POST)
//    public @ResponseBody void addWorker(@PathVariable Long workspaceId, @PathVariable Long workerId) {
//        WorkerIn workerIn = WorkerIn.createWorkerIn(workspaceId, workerId);
//        workspaceFacade.register(workerIn);
//    }
//
//    @RequestMapping(value="/workspaces/{workspaceId}/workers/{workerId}", method = RequestMethod.DELETE)
//    public @ResponseBody void removeWorker(@PathVariable Long workspaceId, @PathVariable Long workerId) {
//        WorkerOut workerOut = WorkerOut.createWorkerOut(workspaceId, workerId);
//        workspaceFacade.register(workerOut);
//    }

}
