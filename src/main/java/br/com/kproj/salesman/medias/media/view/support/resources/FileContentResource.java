package br.com.kproj.salesman.medias.media.view.support.resources;



import br.com.kproj.salesman.medias.media.domain.media.FileContent;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "size",
        "url",
        "mimeType",
        "links"
})
@ResourceItem(name="file-contents", modelReference = FileContent.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileContentResource extends Item {


    private Long id;
    private String name;
    private Integer size;
    private String url;
    private String mimeType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
