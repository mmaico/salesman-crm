package br.com.kproj.salesman.assistants.activities.domain.model.personal;


public enum Status {

    WAITING("Aguardadndo"), IN_PROGRESS("Iniciado"), PROBLEM("Com problemas"), DONE("Finalizado");


    private String message;

    private Status(String value) {
        this.message = value;
    }

    public String get() {
        return message;
    }
}
