package br.com.kproj.salesman.infrastructure.entity.auditing;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="business_proposal_auditing")
public class BusinessProposalAudinting extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(columnDefinition = "LONGTEXT")
    private String info;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="last_update")
    private Date lastUpdate;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
