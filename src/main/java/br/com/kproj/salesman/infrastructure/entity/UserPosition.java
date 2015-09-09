package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users_position")
public class UserPosition extends Identifiable {

    /**
	 *
	 */
	private static final long serialVersionUID = 282685797677443589L;

    @Id
    private Long id;

    @NotNull(message = "position.name")
    private String name;


    public Long getId() {
        return id;
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
