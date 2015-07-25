package br.com.kproj.salesman.infrastructure.entity.person;


import javax.persistence.Entity;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

@Entity
public class Profile extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

    public Profile() {}
	public Profile(Long id) {
		this.setId(id);
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
