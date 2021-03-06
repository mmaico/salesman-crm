package br.com.kproj.salesman.infrastructure.entity.campaigns;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelineEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name="campaings")
public class CampaignEntity extends Identifiable {

	private static final long serialVersionUID = -7486201820229036695L;

    public enum CampaignStatus {
        PLANNING, ABORTED, COMPLETED, IN_PROGRESS
    }

    @Id
    @GeneratedValue
    private Long id;

	@NotNull @Size(min = 2, max = 100)
    private String name;

    private String description;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private CampaignStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @NotNull(message = "user.createdby.is.null")
    private UserEntity createdBy;


    @Override
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }
}
