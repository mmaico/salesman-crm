package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("email")
public class EmailActivity extends TimelineActivity {


    /**
	 * 
	 */
	private static final long serialVersionUID = -2555190729836666717L;

	private String to;
    private String from;
    private String subject;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
