package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
@Media(name="timelines")
@DiscriminatorValue("business_proposal_avaluation_activity")
public class BusinessProposalApprovalActivity extends TimelineActivity {


    /**
	 *
	 */
	private static final long serialVersionUID = 2728388686834419769L;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ApproverStatus avaluation = ApproverStatus.WAITING;

    public BusinessProposalApprovalActivity(){}

    public BusinessProposalApprovalActivity(Long id) {
        this.setId(id);
    }

    public ApproverStatus getAvaluation() {
        return avaluation;
    }

    public void setAvaluation(ApproverStatus avaluation) {
        this.avaluation = avaluation;
    }
}
