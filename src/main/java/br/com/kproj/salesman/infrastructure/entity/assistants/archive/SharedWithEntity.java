package br.com.kproj.salesman.infrastructure.entity.assistants.archive;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.SharedTypeEntity;

import javax.persistence.*;

@Entity
@Table(name="shared_files_with")
public class SharedWithEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="file_info_id")
    private FileInfoEntity fileInfoEntity;

    @Enumerated(EnumType.STRING)
    private SharedTypeEntity type;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public FileInfoEntity getFileInfoEntity() {
        return fileInfoEntity;
    }

    public void setFileInfoEntity(FileInfoEntity fileInfoEntity) {
        this.fileInfoEntity = fileInfoEntity;
    }

    public SharedTypeEntity getType() {
        return type;
    }

    public void setType(SharedTypeEntity type) {
        this.type = type;
    }
}
