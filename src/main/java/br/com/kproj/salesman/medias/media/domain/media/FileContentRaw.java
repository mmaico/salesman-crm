package br.com.kproj.salesman.medias.media.domain.media;


import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class FileContentRaw implements ValueObject {

    public FileContentRaw(String file) {
        this.file = file;
    }

    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public static FileContentRaw createRaw(String file) {
        return new FileContentRaw(file);
    }
}
