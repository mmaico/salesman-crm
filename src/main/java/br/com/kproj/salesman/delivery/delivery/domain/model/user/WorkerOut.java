package br.com.kproj.salesman.delivery.delivery.domain.model.user;


public class WorkerOut {

    private Long workerId;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public static WorkerOut createWorkerOut(Long workerId) {
        WorkerOut workerOut = new WorkerOut();
        workerOut.setWorkerId(workerId);

        return workerOut;
    }
}
