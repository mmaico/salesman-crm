package br.com.kproj.salesman.administration.users.infrastructure.persistence.springdata;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
public class UserMongo {

  @Id
  private String id;

  private String name;
  private String lastName;
  private Date birth;
  private List<AddressMongo> addresses;

  public UserMongo(){}

  public UserMongo(String name, String lastName, Date birth) {
    this.name = name;
    this.lastName = lastName;
    this.birth = birth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public List<AddressMongo> getAddresses() {
    return addresses;
  }

  public void setAddresses(
      List<AddressMongo> addresses) {
    this.addresses = addresses;
  }
}
