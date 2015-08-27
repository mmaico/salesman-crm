package br.com.kproj.salesman.infrastructure.entity.person.privider;


public interface ProviderCompany extends Provider {

    String getTradingName();

    void setTradingName(String tradingName);

    String getCnpj();

    void setCnpj(String cnpj);

    String getIe();

    void setIe(String ie);

    String getCcm();

    void setCcm(String ccm);
}
