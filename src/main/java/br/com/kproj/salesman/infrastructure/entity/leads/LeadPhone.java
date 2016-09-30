package br.com.kproj.salesman.infrastructure.entity.leads;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="lead_phones")
public class LeadPhone extends Identifiable {

    public enum PhoneType {
        BUSINESS, HOME_PHONE, CELL
    }
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @ExcludeField
    private LeadEntity lead;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LeadEntity getLead() {
        return lead;
    }

    public void setLead(LeadEntity lead) {
        this.lead = lead;
    }
}
