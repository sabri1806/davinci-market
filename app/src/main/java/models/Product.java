package models;

/**
 * Created by Sabrina on 16/05/2017.
 */

public class Product {

  private int id;
  private String name;
  private String description;
  private int catId;

  public int getCatId() {
    return catId;
  }

  public void setCatId(int catId) {
    this.catId = catId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



}
