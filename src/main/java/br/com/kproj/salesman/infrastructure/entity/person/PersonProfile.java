package br.com.kproj.salesman.infrastructure.entity.person;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person_profiles")
public class PersonProfile extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public static final Long COMPANY_PROFILE = 1l;
    public static final Long INDIVIDUAL_PROFILE = 2l;

    @Id
    private Long id;
	
	private String name;

    public PersonProfile() {}
	public PersonProfile(Long id) {
		this.setId(id);
	}
	public PersonProfile(Long id, String name) {
		this.setId(id);
		this.name = name;
	}

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



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile {");
        sb.append("id=").append(getId());
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
