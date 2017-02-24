package br.com.kproj.salesman.medias.media.domain.media;


import br.com.kproj.salesman.medias.media.domain.storage.Storage;

public class MediaInStorage {

    public final FileContentRaw fileContentRaw;
    private final Storage storage;


    public MediaInStorage(FileContentRaw fileContentRaw, Storage storage) {
        this.fileContentRaw = fileContentRaw;
        this.storage = storage;
    }

    public FileContentRaw getFileContentRaw() {
        return fileContentRaw;
    }

    public Storage getStorage() {
        return storage;
    }

    public static MediaInStorage create(FileContentRaw fileContentRaw, Storage storage) {
        return new MediaInStorage(fileContentRaw, storage);
    }
}
