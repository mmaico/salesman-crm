package br.com.kproj.salesman.delivery.delivery.domain.model.delivery;


import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;

public class WorkerIn {

    private Long deliveryId;
    private Long workerId;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
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

    public static WorkerIn createWorkerIn(Long workspaceId, Long workerId) {
        WorkerIn workerIn = new WorkerIn();
        workerIn.setWorkerId(workerId);
        workerIn.setDeliveryId(workspaceId);

        return workerIn;
    }
}
