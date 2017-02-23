package br.com.kproj.salesman.medias.storage.view.support.resources;


import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "phone",
        "position",
        "customer",
        "links"
})
@ResourceItem(name="storage", modelReference = StorageDefinition.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageDefinitionResource extends Item {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String position;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
