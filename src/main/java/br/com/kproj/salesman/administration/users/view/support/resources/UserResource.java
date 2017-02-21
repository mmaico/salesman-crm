package br.com.kproj.salesman.administration.users.view.support.resources;


import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="users", modelReference = User.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource extends Item {

    private Long id;

    private String login;

    private String password;

    @NotNull(message = "user.name.cannot.be.null")
    private String name;

    private String lastname;

    private String email;

    private BranchResource branch;

    private PositionResource position;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Selectable(expression = "branch", externalLink = true)
    public BranchResource getBranch() {
        return branch;
    }

    public void setBranch(BranchResource branch) {
        this.branch = branch;
    }

    @Selectable(expression = "position", externalLink = true)
    public PositionResource getPosition() {
        return position;
    }

    public void setPosition(PositionResource position) {
        this.position = position;
    }

    @JsonIgnore
    public Long getBranchId() {
        return this.branch == null ? null : this.branch.getId();
    }

    @JsonIgnore
    public Long getPositionId() {
        return this.position == null ? null : this.position.getId();
    }


}
