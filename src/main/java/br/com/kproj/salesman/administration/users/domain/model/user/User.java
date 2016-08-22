package br.com.kproj.salesman.administration.users.domain.model.user;

import br.com.kproj.salesman.administration.users.domain.model.branch.Branch;
import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import javax.validation.constraints.NotNull;


public class User extends ModelIdentifiable {

    private Long id;

    private String login;

    @NotNull(message = "user.password.cannot.be.null")
    private String password;

    private String passwordConfirm;

    @NotNull(message = "user.name.cannot.be.null")
    private String name;

    private String lastname;

    private String email;

    @ExcludeField
    private byte[] avatar;

    private Branch branch;

    private Position position;


    @Override
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
