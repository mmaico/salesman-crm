package br.com.kproj.salesman.assistants.archive.domain.model.archive;

import br.com.kproj.salesman.assistants.archive.domain.model.owner.Owner;
import br.com.kproj.salesman.assistants.archive.domain.model.participant.Participant;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Attribute;
import com.github.mmaico.shared.annotations.Model;


import java.util.List;

@Model
public class Archive extends ModelIdentifiable {

    private Long id;
    private String title;
    private String description;
    private Owner owner;

    @Attribute(destinationName = "sharedWithEntity")
    private List<Participant> participants;

    @Attribute(destinationName = "file")
    private PhysicalFile physical;
    private Boolean isPublic = Boolean.FALSE;

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

    public PhysicalFile getPhysical() {
        return physical;
    }

    public void setPhysical(PhysicalFile physical) {
        this.physical = physical;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
