package br.com.kproj.salesman.delivery.workspaces.domain.model.workspace;


import br.com.kproj.salesman.delivery.workspaces.domain.model.user.Worker;

public class WorkerOut {

    private Long workspaceId;
    private Long workerId;

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public Worker getWorker() {
        return new Worker(workerId);
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public static WorkerOut createWorkerOut(Long workspaceId, Long workerId) {
        WorkerOut workerOut = new WorkerOut();
        workerOut.setWorkerId(workerId);
        workerOut.setWorkspaceId(workspaceId);

        return workerOut;
    }
}
