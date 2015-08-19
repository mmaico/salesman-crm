package br.com.kproj.salesman.infrastructure.entity.location;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

@Entity
@Table(name="countries")
public class Country extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932439980875323084L;

	private String name;
	
	@Column(name= "bacen_code")
	private String bacenCode;
	
	@OneToMany(mappedBy = "country")
	private List<State> states;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBacenCode() {
		return bacenCode;
	}
	public void setBacenCode(String bacenCode) {
		this.bacenCode = bacenCode;
	}
	
	
}
