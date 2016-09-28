package br.com.kproj.salesman.assistants.archive.domain.model.owner;

import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class OwnerChangeArchive implements ValueObject {

    private final Owner owner;
    private final Archive archive;


    public OwnerChangeArchive(Owner owner, Archive archive) {
        this.owner = owner;
        this.archive = archive;
    }

    public Owner getOwner() {
        return owner;
    }

    public Archive getArchive() {
        return archive;
    }

    public OwnerChangeArchive createChangeArchive(Owner owner, Archive archive) {
        return new OwnerChangeArchive(owner, archive);
    }
}
