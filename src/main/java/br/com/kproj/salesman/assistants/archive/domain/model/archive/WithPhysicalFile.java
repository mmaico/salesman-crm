package br.com.kproj.salesman.assistants.archive.domain.model.archive;


import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class WithPhysicalFile implements ValueObject {

    private final Archive archive;

    public WithPhysicalFile(Archive archive) {
        this.archive = archive;
    }

    public Archive getArchive() {
        return archive;
    }
}
