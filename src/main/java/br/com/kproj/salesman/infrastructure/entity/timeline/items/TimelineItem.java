package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.MediaStorage;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Media(name="timelines")
@Table(name="timeline_item")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_item", discriminatorType=DiscriminatorType.STRING)
public class TimelineItem extends Identifiable {

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
    @OrderBy("creation ASC")
    @MediaStorage(name="files")
    private List<AppFile> files;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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

    public List<AppFile> getFiles() {
        return files;
    }

    public void setFiles(List<AppFile> files) {
        this.files = files;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
