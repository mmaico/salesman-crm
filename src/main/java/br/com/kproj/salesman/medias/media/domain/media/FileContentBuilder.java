package br.com.kproj.salesman.medias.media.domain.media;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.medias.media.domain.storage.Storage;

public class FileContentBuilder extends AbstractBuilder<FileContent> {

    private String name;
    private Integer size;
    private String cdnUrl;
    private String mimeType;
    private Storage storage;

    public FileContentBuilder() {
        this.entity = new FileContent();
    }

    public FileContentBuilder(Long id) {
        this();
        this.entity.setId(id);
    }

    public static FileContentBuilder createFileContent() {
        return new FileContentBuilder();
    }
    public static FileContentBuilder createFileContent(Long id) {
        return new FileContentBuilder(id);
    }

    public FileContentBuilder withName(String name) {
        this.entity.setName(name);
        return this;
    }

    public FileContentBuilder withSize(Integer size) {
        this.entity.setSize(size);
        return this;
    }

    public FileContentBuilder withCdnUrl(String url) {
        this.entity.setCdnUrl(url);
        return this;
    }

    public FileContentBuilder withMimeType(String mimeType) {
        this.entity.setMimeType(mimeType);
        return this;
    }

    public FileContentBuilder withStorage(Storage storage) {
        this.entity.setStorage(storage);
        return this;
    }


}
