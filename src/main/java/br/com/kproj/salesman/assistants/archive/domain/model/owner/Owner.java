package br.com.kproj.salesman.assistants.archive.domain.model.owner;

import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.assistants.archive.domain.model.archive.ArchiveRepository;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Model
public class Owner extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ArchiveRepository repository;

    public Owner() {
        AutowireHelper.autowire(this);
    }

    public Optional<Archive> save(Archive archive) {
        return repository.save(archive);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
