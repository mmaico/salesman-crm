package br.com.kproj.salesman.infrastructure.entity.location;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class State extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 699235843576341807L;

    @Id
    private Long id;

	private String name;

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

}
