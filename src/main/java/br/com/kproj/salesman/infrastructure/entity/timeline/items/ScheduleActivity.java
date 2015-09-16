package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("schedule")
public class ScheduleActivity extends TimelineActivity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9181186990350770213L;

	private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


}
