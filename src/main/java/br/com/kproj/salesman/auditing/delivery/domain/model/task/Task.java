package br.com.kproj.salesman.auditing.delivery.domain.model.task;


import br.com.kproj.salesman.auditing.delivery.domain.model.audit.Auditing;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


import java.util.Optional;

@Model
public class Task extends ModelIdentifiable {

    private Long id;
    private String currentVersion;

    public Task wasChanged() {
        return this;
    }

    public Boolean whenComparedWith(Optional<Auditing> auditing) {
        if (auditing.isPresent()) {
            return auditing.get().getData().equalsIgnoreCase(this.currentVersion);
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
}
