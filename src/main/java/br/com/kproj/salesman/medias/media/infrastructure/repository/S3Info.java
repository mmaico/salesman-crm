package br.com.kproj.salesman.medias.media.infrastructure.repository;


public class S3Info {
    private final String name;
    private final String type;
    private final String url;

    public S3Info (String name, String type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
