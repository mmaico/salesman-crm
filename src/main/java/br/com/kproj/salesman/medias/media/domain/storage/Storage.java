package br.com.kproj.salesman.medias.media.domain.storage;


import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.medias.media.domain.media.FileContent;
import br.com.kproj.salesman.medias.media.domain.media.FileContentRaw;
import br.com.kproj.salesman.medias.media.domain.media.FileContentRepository;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Model
public class Storage extends ModelIdentifiable {

    private Long id;
    private String name;

    @Autowired
    private FileContentRepository repository;

    public Storage(String name) {
        this();
        this.name = name;
    }
    public Storage(Long id) {
        this();
        this.id = id;
    }

    public Storage() {
        AutowireHelper.autowire(this);
    }

    public FileContent thisFile(FileContentRaw raw) {
        return repository.store(raw, this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Storage createStorage(String name) {
        return new Storage(name);
    }
}
