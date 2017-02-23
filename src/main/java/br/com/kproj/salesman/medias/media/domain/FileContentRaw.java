package br.com.kproj.salesman.medias.media.domain;


import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class FileContentRaw implements ValueObject {

    public FileContentRaw(String image) {
        this.image = image;
    }

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static FileContentRaw createRaw(String image) {
        return new FileContentRaw(image);
    }
}
