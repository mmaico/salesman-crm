package br.com.kproj.salesman.infrastructure.entity.enums;


public enum TaskStatus {

    WAITING("Aguardadndo"), STATED("Iniciado"), PROBLEM("Com problemas"), DONE("Finalizado");

    private String message;

    private TaskStatus(String value) {
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
