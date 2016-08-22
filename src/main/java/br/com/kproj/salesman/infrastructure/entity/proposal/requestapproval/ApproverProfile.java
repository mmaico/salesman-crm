package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;

import javax.persistence.*;


@Entity
@Table(name="proposal_aprover_profile")
public class ApproverProfile extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name="user_approver_id")
    @ExcludeField
    private UserEntity approver;

    private Boolean available = Boolean.FALSE;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getApprover() {
        return approver;
    }

    public void setApprover(UserEntity approver) {
        this.approver = approver;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
