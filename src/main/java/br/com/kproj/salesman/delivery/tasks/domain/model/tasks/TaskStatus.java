package br.com.kproj.salesman.delivery.tasks.domain.model.tasks;


public enum TaskStatus {

    WAITING("Aguardadndo"), STATED("Iniciado"), PROBLEM("Com problemas"), DONE("Finalizado");

    private String message;

    TaskStatus(String value) {
        this.message = value;
    }

    public String get() {
        return message;
    }

    public static TaskStatus get(String status) {

        for (TaskStatus value: values()) {
            if (value.name().equalsIgnoreCase(status)) {
                return value;
            }
        }
        return null;
    }
}
