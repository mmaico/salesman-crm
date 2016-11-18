package br.com.kproj.salesman.delivery.tasks.domain.model.tasks;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Model
public class Task extends ModelIdentifiable {

    private Long id;

    private String title;
    private String description;

    @NotNull(message = "task.deadline.not.informed")
    private Date deadline;

    private TaskStatus status;
    private List<Checklist> checklists;
    private SalesOrder salesOrder;
    private List<User> signedBy;

    @Autowired
    private ChecklistRepository checklistRepository;

    public Task() {
        AutowireHelper.autowire(this);
    }


    public void addCheckList(Checklist checklist) {
        checklistRepository.register(checklist, this);
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<Checklist> checklists) {
        this.checklists = checklists;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public List<User> getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(List<User> signedBy) {
        this.signedBy = signedBy;
    }

}
