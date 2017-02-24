package br.com.kproj.salesman.medias.media.domain.media;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.medias.media.domain.storage.Storage;

public interface FileContentRepository extends BaseRepository<FileContent, Long> {

    FileContent store(FileContentRaw imageRaw, Storage storage);

}
