package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="proposal_approver")
public class ApproverEntity extends Identifiable {

    @Id
    private Long id;

    @OneToOne
    @NotNull(message = "approver.user.cannot.be.null")
    @JoinColumn(name="user_approver_id")
    private UserEntity approver;

    @Column(name = "available")
    private Boolean available;

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
