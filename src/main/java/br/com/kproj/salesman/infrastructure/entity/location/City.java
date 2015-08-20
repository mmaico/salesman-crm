package br.com.kproj.salesman.infrastructure.entity.location;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
    private String stateAcronym;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getStateAcronym() {
        return stateAcronym;
    }

    public void setStateAcronym(String stateAcronym) {
        this.stateAcronym = stateAcronym;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
