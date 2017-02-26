package br.com.kproj.salesman.timelines.activities.domain.model.media;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import com.trex.shared.annotations.Model;

@Model
public class MediaRelationship extends ModelIdentifiable {

    private Long id;
    private Activity activity;
    private Media media;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
