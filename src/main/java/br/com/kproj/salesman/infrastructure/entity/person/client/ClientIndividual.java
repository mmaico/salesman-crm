package br.com.kproj.salesman.infrastructure.entity.person.client;


public interface ClientIndividual extends Client {

    String getCpf();

    void setCpf(String cpf);

    String getLastname();

    void setLastname(String lastname);
}
