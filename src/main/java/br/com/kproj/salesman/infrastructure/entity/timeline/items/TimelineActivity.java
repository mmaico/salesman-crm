package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelineEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="timeline_activities")
public class TimelineActivity extends Identifiable {

    public enum TagEntity {
        CALL, NOTE, EMAIL, MEETING
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = 8655772230845390696L;

	@Id
    @GeneratedValue
    private Long id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date creation;

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="timeline_item_files", joinColumns=@JoinColumn(name="timeline_item_id"),
            inverseJoinColumns=@JoinColumn(name="appfile_id"))
    private List<AppFileEntity> files;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="timeline_id")
    private TimelineEntity timeline;

    @Enumerated(value = EnumType.STRING)
    @Column(name="TAG")
    private TagEntity tag;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public List<AppFileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<AppFileEntity> files) {
        this.files = files;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TimelineEntity getTimeline() {
        return timeline;
    }

    public void setTimeline(TimelineEntity timeline) {
        this.timeline = timeline;
    }


    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }
}
