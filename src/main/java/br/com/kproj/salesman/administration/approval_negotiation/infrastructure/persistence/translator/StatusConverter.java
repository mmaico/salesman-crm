package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.translator;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.PersonApproval;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApprovalItemEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApprovalItemEntity.StatusEntity;
import com.github.mmaico.shared.converters.AttributeEntityConverter;


public class StatusConverter implements AttributeEntityConverter<PersonApproval, ApprovalItemEntity> {

    @Override
    public Object convertToEntityAttribute(PersonApproval model) {

        if (PersonApproval.Status.APPROVED.equals(model.getStatus())) {
            return StatusEntity.APPROVED;
        } else if (PersonApproval.Status.REJECTED.equals(model.getStatus())) {
            return StatusEntity.DISAPPROVED;
        } else if (PersonApproval.Status.WAITING.equals(model.getStatus())) {
            return StatusEntity.WAITING;
        }

        return null;
    }

    @Override
    public Object convertToBusinessModel(ApprovalItemEntity entity) {
        if (StatusEntity.APPROVED.name().equals(entity.getStatus())) {
            return PersonApproval.Status.APPROVED;
        } else if (StatusEntity.DISAPPROVED.name().equals(entity.getStatus())) {
            return PersonApproval.Status.REJECTED;
        } else if (StatusEntity.WAITING.name().equals(entity.getStatus())) {
            return PersonApproval.Status.WAITING;
        }

        return null;
    }
}
