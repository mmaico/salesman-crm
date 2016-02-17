package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import org.apache.commons.io.IOUtils;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class User extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 282685797677443589L;

    @Id
    @GeneratedValue
    private Long id;

	@NotNull(message = "user.login.cannot.be.null")
    private String login;

    @NotNull(message = "user.password.cannot.be.null")
    private String password;

    @Transient
    private String passwordConfirm;

    @NotNull(message = "user.name.cannot.be.null")
    private String name;

    private String lastname;

    private String email;
    
    @Basic(fetch = FetchType.LAZY)
	@Column(name = "avatar", length = 200000)
	@Lob
	private byte[] avatar;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="position_id")
    private UserPosition position;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="branch_id")
    private Branch branch;

    @OneToOne(mappedBy = "approver")
    private ApproverProfile approverProfile;

    @OneToOne
    @JoinColumn(name="calendar_id")
    private Calendar calendar;

    @Transient
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public User() {
        super();
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    @Override
    public Long getId() {
        return this.id;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
	
	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

    public UserPosition getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(UserPosition position) {
        this.position = position;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public ApproverProfile getApproverProfile() {
        return approverProfile;
    }

    public void setApproverProfile(ApproverProfile approverProfile) {
        this.approverProfile = approverProfile;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static byte[] getDefaultAvatar() {
        InputStream inputStream = User.class.getResourceAsStream("/imagens/avatar.png");
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            return null;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
