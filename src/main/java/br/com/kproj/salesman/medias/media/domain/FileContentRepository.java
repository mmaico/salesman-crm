package br.com.kproj.salesman.medias.media.domain;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

public interface FileContentRepository extends BaseRepository<FileContent, Long> {

    FileContent store(FileContentRaw imageRaw);

}
