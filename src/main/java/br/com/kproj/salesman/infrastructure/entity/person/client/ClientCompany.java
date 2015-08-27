package br.com.kproj.salesman.infrastructure.entity.person.client;


public interface ClientCompany  extends Client {

    String getTradingName();

    void setTradingName(String tradingName);

    String getCnpj();

    void setCnpj(String cnpj);

    String getIe();

    void setIe(String ie);

    String getCcm();

    void setCcm(String ccm);
}
