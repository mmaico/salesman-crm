package br.com.kproj.salesman.timelines.activities.domain.model.media;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Media extends ModelIdentifiable {


    private Long id;

    public Media() {}
    public Media(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
