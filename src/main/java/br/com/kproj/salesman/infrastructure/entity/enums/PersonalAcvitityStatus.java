package br.com.kproj.salesman.infrastructure.entity.enums;


public enum PersonalAcvitityStatus {

    WAITING("Aguardadndo"), IN_PROGRESS("Iniciado"), PROBLEM("Com problemas"), DONE("Finalizado");

    private String message;

    private PersonalAcvitityStatus(String value) {
        this.message = value;
    }

    public String get() {
        return message;
    }

    public static PersonalAcvitityStatus get(String status) {

        for (PersonalAcvitityStatus value: values()) {
            if (value.name().equalsIgnoreCase(status)) {
                return value;
            }
        }
        return null;
    }
}
