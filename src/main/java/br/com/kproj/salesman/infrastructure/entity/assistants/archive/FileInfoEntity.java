package br.com.kproj.salesman.infrastructure.entity.assistants.archive;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.MediaStorage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Media(name="file_infos")
@Entity
@Table(name="file_infos")
public class FileInfoEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="owner_id")
    @NotNull(message = "files.user.is.required")
    private UserEntity owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fileInfo")
    private List<SharedWithEntity> sharedWith;

    @Column(name="is_public")
    private Boolean isPublic = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name="app_file_id")
    @MediaStorage(name="files")
    private AppFile file;

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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<SharedWithEntity> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<SharedWithEntity> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public Boolean isPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public AppFile getFile() {
        return file;
    }

    public void setFile(AppFile file) {
        this.file = file;
    }
}
