package br.com.kproj.salesman.medias.media.domain.media;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class FileContentBuilder extends AbstractBuilder<FileContent> {

    private String name;
    private Integer size;
    private String cdnUrl;
    private String mimeType;

    public FileContentBuilder() {
        this.entity = new FileContent();
    }

    public FileContentBuilder(Long id) {
        this();
        this.entity.setId(id);
    }

    public static FileContentBuilder createImage() {
        return new FileContentBuilder();
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


}
