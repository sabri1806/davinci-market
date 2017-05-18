package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabrina on 16/05/2017.
 */

public class Category {

  private List<Product> products = new ArrayList<Product>();
  private int id;
  private String name;
  private String description;

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

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }


}
