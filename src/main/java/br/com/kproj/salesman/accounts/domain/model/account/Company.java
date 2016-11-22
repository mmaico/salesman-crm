package br.com.kproj.salesman.accounts.domain.model.account;

import com.trex.shared.annotations.Model;

@Model
public class Company extends Account {

    private String cnpj;
    private String tradingName;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }
}
