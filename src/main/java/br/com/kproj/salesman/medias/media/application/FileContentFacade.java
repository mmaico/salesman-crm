package br.com.kproj.salesman.medias.media.application;



import br.com.kproj.salesman.medias.media.domain.FileContent;
import br.com.kproj.salesman.medias.media.domain.FileContentRaw;

import java.util.Optional;

public interface FileContentFacade {

    FileContent store(FileContentRaw imageRaw);

    Optional<FileContent> findOne(Long id);
}
