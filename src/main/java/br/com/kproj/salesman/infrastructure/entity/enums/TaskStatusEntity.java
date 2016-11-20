package br.com.kproj.salesman.infrastructure.entity.enums;


public enum TaskStatusEntity {

    WAITING("Aguardadndo"), STATED("Iniciado"), PROBLEM("Com problemas"), DONE("Finalizado");

    private String message;

    TaskStatusEntity(String value) {
        this.message = value;
    }

    public String get() {
        return message;
    }

    public static TaskStatusEntity get(String status) {

        for (TaskStatusEntity value: values()) {
            if (value.name().equalsIgnoreCase(status)) {
                return value;
            }
        }
        return null;
    }
}
