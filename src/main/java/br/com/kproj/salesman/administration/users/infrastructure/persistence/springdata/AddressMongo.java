package br.com.kproj.salesman.administration.users.infrastructure.persistence.springdata;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "addresses")
public class AddressMongo {

  @Id
  private ObjectId id;
  private String street;
  private String city;

  public AddressMongo() {}
  public AddressMongo(String street, String city) {
    this.id = ObjectId.get();
    this.street = street;
    this.city = city;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
