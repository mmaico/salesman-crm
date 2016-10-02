package br.com.kproj.salesman.assistants.archive.infrastructure.persistence.translate;

import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.infrastructure.converters.Specification;
import br.com.kproj.salesman.infrastructure.converters.TwoWayMerge;
import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfoEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static com.trex.dsl.ConditionalSet.set;

@Component
public class ArchiveTranslate implements TwoWayMerge<Archive, FileInfoEntity> {


    private ParticipantsTranslate participantsTranslate;
    private PhysicalFileTranslate physicalFileTranslate;

    @Autowired
    public ArchiveTranslate(ParticipantsTranslate participantsTranslate, PhysicalFileTranslate physicalFileTranslate) {
        this.participantsTranslate = participantsTranslate;
        this.physicalFileTranslate = physicalFileTranslate;

    }

    @Override
    public void merge(Archive source, FileInfoEntity target, Specification... specs) {
        target.setDescription(source.getDescription());

        set(target).ifPresent(source.getFields()).setTitle(source.getTitle());
        set(target).ifPresent(source.getFields()).setDescription(source.getDescription());
        set(target).when(source.getFields().contains("isPublic")).setPublic(source.getIsPublic());

        if (isEmptySafe(target.getSharedWith())) {
            target.setSharedWith(Lists.newArrayList());
        }
        this.participantsTranslate.merge(source.getParticipants(), target.getSharedWith());

        if (source.getPhysical() != null && target.getFile() == null) {
            target.setFile(new AppFile());
        }

        physicalFileTranslate.merge(source.getPhysical(), target.getFile());

    }

    @Override
    public void mergeBack(FileInfoEntity source, Archive target, Specification... specs) {

    }
}
