package br.com.kproj.salesman.accounts.domain.model.account;

import com.trex.shared.annotations.Model;

@Model
public class Individual extends Account {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
