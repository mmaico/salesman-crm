package br.com.kproj.salesman.negotiation.view.dto;


import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;

public class TemperatureDTO {

    private ProposalTemperature temperature;

    public ProposalTemperature getTemperature() {
        return temperature;
    }

    public void setTemperature(ProposalTemperature temperature) {
        this.temperature = temperature;
    }
}
