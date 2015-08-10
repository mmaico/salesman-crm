package br.com.kproj.salesman.infrastructure.entity.person;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.Entity;

@Entity
public class PersonProfile extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

    public PersonProfile() {}
	public PersonProfile(Long id) {
		this.setId(id);
	}
	public PersonProfile(Long id, String name) {
		this.setId(id);
		this.name = name;
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
