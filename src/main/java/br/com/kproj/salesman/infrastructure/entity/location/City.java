package br.com.kproj.salesman.infrastructure.entity.location;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.hibernate.envers.Audited;

import javax.persistence.*;


@Entity
@Table(name="cities")
@Audited
public class City extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2757567678579808171L;

    @Id
    @GeneratedValue
    private Long id;

	private String name;

    private String code;

    @Column(name="state_acronym")
    private String stateAcronym;



    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
