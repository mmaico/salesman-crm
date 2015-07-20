package br.com.kproj.salesman.infrastructure.entity.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("company")
public class Company extends Person {

    @Size(max = 30, message = "company.invalid.trandingname")
    private String tradingName;

    @Size(max = 15, message = "company.invalid.cnpj")
    private String cnpj;

    private String ie;

    private String ccm;


    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getCcm() {
        return ccm;
    }

    public void setCcm(String ccm) {
        this.ccm = ccm;
    }
}
