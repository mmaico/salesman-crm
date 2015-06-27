package br.com.kproj.salesman.infrastructure.entity;


public class State extends Identifiable {

    private String name;

    public State() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
