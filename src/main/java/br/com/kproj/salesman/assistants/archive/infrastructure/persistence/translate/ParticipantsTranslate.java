package br.com.kproj.salesman.assistants.archive.infrastructure.persistence.translate;

import br.com.kproj.salesman.assistants.archive.domain.model.participant.Participant;
import br.com.kproj.salesman.infrastructure.converters.Specification;
import br.com.kproj.salesman.infrastructure.converters.TwoWayMerge;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.SharedWithEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipantsTranslate implements TwoWayMerge<List<Participant>, List<SharedWithEntity>> {


    @Override
    public void merge(List<Participant> source, List<SharedWithEntity> target, Specification... specs) {


    }

    @Override
    public void mergeBack(List<SharedWithEntity> source, List<Participant> target, Specification... specs) {

    }
}
