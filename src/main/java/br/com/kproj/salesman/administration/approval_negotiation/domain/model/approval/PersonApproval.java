package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.translator.StatusConverter;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Attribute;
import com.trex.shared.annotations.Model;

@Model
public class PersonApproval extends ModelIdentifiable {

    public enum Status {
        APPROVED, WAITING, REJECTED;

        public static Status get(String name) {

            for (Status status:values()) {
                if (status.name().equalsIgnoreCase(name)) {
                    return status;
                }
            }
            return null;
        }
    }

    private Long id;

    private Approver approver;

    @Attribute(converter = StatusConverter.class)
    private Status status;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
