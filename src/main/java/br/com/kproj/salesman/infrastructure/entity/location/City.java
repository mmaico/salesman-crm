package br.com.kproj.salesman.infrastructure.entity.location;

import javax.persistence.*;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;


@Entity
@Table(name="cities")
public class City extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2757567678579808171L;

	private String name;

    private String code;

    @Column(name="state_acronym")
    private String StateAcronym;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getStateAcronym() {
        return StateAcronym;
    }

    public void setStateAcronym(String stateAcronym) {
        StateAcronym = stateAcronym;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
