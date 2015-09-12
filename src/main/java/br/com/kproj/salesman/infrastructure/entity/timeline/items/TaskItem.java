package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@DiscriminatorValue("task")
public class TaskItem extends TimelineItem {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6505997363895736083L;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
