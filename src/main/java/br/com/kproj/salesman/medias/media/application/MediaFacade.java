package br.com.kproj.salesman.medias.media.application;



import br.com.kproj.salesman.medias.media.domain.media.FileContent;
import br.com.kproj.salesman.medias.media.domain.media.FileContentRaw;
import br.com.kproj.salesman.medias.media.domain.media.MediaInStorage;

import java.util.Optional;

public interface MediaFacade {

    FileContent store(MediaInStorage mediaInStorage);

    Optional<FileContent> findOne(Long id);
}
