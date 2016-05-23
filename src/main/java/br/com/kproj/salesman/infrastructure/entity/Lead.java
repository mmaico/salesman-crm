package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.auditing.infrastructure.ExcludeAuditingField;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**

     Status
     Lead Owner
     Saudacao(Sr, Sra, Dr, Prof)
     Nome
     Cargo
     Empresa (string)
     Email
     Telefone
     Tipo de atividade da empresa
     numero de funcionarios
     celular
     load source (web, email, google)
     Endereco (rua, cidade, estado, cep, pais)

 **/

@Entity
@Table(name="leads")
public class Lead extends Identifiable implements TimelinePresent {

	private static final long serialVersionUID = -7486201820229036695L;

    @Id
    @GeneratedValue
    private Long id;

	@NotNull @Size(min = 2, max = 100)
    private String name;

    @Email
    private String email;

    private String phone;

    private String position;

    //@OneToOne(mappedBy = "contact")
    private Timeline timeline;

    public Lead() {}

    public Lead(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
}
