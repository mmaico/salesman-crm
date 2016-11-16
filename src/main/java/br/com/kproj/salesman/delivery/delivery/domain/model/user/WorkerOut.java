package br.com.kproj.salesman.delivery.delivery.domain.model.user;


import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;

public class WorkerOut {

    private Long deliveryId;
    private Worker worker;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Delivery getDelivery() {
        return new Delivery(this.deliveryId);
    }

    public static WorkerOut createWorkerOut(Long deliveryId, Worker worker) {
        WorkerOut workerOut = new WorkerOut();
        workerOut.setWorker(worker);
        workerOut.setDeliveryId(deliveryId);

        return workerOut;
    }
}
