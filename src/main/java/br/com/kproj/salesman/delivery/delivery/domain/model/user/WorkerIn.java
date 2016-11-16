package br.com.kproj.salesman.delivery.delivery.domain.model.user;


import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;

public class WorkerIn {

    private Long deliveryId;
    private Long userId;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Delivery getDelivery() {
        return new Delivery(this.deliveryId);
    }

    public static WorkerIn createWorkerIn(Long deliveryId, Long userId) {
        WorkerIn workerIn = new WorkerIn();
        workerIn.setDeliveryId(deliveryId);
        workerIn.setUserId(userId);

        return workerIn;
    }
}
