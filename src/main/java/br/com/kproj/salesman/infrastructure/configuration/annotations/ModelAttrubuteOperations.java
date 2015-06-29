package br.com.kproj.salesman.infrastructure.configuration.annotations;


public enum ModelAttrubuteOperations {

    UPDATE("update"), CREATE("create");

    private String operation;

    private ModelAttrubuteOperations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
