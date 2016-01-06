package br.com.kproj.salesman.infrastructure.entity.person;

import br.com.kproj.salesman.infrastructure.entity.person.client.ClientCompany;
import br.com.kproj.salesman.infrastructure.entity.person.privider.ProviderCompany;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("company")
public class Company extends Person implements ClientCompany, ProviderCompany {

	private static final long serialVersionUID = 4479758448493548647L;

	@Size(max = 120, message = "company.invalid.trandingname")
    @Column(name="trading_name")
    private String tradingName;

    @Size(max = 20, message = "company.invalid.cnpj")
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

    @Override
    public Company to() {
        return this;
    }
}
