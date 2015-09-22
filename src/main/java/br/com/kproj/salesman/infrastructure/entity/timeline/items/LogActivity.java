package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
@Media(name="timelines")
@DiscriminatorValue("logactivity")
public class LogActivity extends TimelineActivity {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2728388686834419769L;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LogActivityTypeEnum type;

    public LogActivityTypeEnum getType() {
        return type;
    }

    public void setType(LogActivityTypeEnum type) {
        this.type = type;
    }
}
