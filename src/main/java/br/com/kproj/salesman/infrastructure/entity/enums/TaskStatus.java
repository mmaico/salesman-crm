package br.com.kproj.salesman.infrastructure.entity.enums;


public enum TaskStatus {

    WATTING("Aguardadndo"), STATED("Iniciado"), PROBLEM("Com problemas"), DONE("Finalizado");

    private String message;

    private TaskStatus(String value) {
        this.message = value;
    }

    public String get() {
        return message;
    }
}
