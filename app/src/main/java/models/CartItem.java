package models;

import java.util.Date;
import java.util.List;

/**
 * Created by Sabrina on 16/05/2017.
 */

public class CartItem {
  //"CREATE TABLE CartList(id INTEGER PRIMARY KEY NOT NULL, name TEXT, description TEXT, cartDate DATE, status BOOLEAN, )";
  private int id;
  private String name;
  private String description;
  private Date cartDate;
  private Boolean status;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCartDate() {
    return cartDate;
  }

  public void setCartDate(Date cartDate) {
    this.cartDate = cartDate;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}
