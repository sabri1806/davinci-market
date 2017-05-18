package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dbHelpers.CategorySQLiteHelper;
import dbHelpers.ProductSQLiteHelper;
import models.Category;
import models.Product;

/**
 * Created by Sabrina on 17/05/2017.
 */

public class ProductUtils {

  private ProductSQLiteHelper productHelper;
  private SQLiteDatabase db;
  private CategoryUtils catUtil;


  public void initDb(Context context, List<Category> categories){

    productHelper = new ProductSQLiteHelper(context, "dbDavinci1", null, 1);
    db = productHelper.getWritableDatabase();

    productHelper.clearTable(db);

    createProducts(categories);
  /*  prod1.setId(1);
    prod1.setName("Sanatorio");
    prod1.setDescription("Todo verde");

    Category cat2 = new Category();
    cat2.setId(2);
    cat2.setName("Gomeria");
    cat2.setDescription("calnee");
*/


    //create(cat1);
    //create(cat2);

  }

  public void createProducts(List<Category> categories){
    Product p ;
    for (Category c:categories) {
      switch (c.getName()){
        case "verduleria":
          p = new Product();
          p.setId(1);
          p.setName("tomate");
          p.setDescription("redondo");
          p.setCatId(c.getId());
          createProduct(p);
          break;
        case "carniceria":
          p = new Product();
          p.setId(2);
          p.setName("peceto");
          p.setDescription("milanesa");
          p.setCatId(c.getId());
          createProduct(p);
          break;
        case "panaderia":
          p = new Product();
          p.setId(3);
          p.setName("pan");
          p.setDescription("casero");
          p.setCatId(c.getId());
          createProduct(p);
          break;

      }
    }
  }

  private void createProduct(Product p){
    if(db != null){
      ContentValues newRegistry = new ContentValues();
      newRegistry.put("id", p.getId());
      newRegistry.put("name", p.getName());
      newRegistry.put("description", p.getDescription());
      newRegistry.put("product_cat", p.getCatId());

      db.insert("Product", null, newRegistry);
    }
  }

  public List<Product> getAll(){
    Cursor cursor = db.rawQuery("SELECT * FROM Product", null);
    List<Product> products = new ArrayList<>();

    if (cursor.moveToFirst()) {
      while(cursor.isAfterLast()== false){

        Integer id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String description = cursor.getString(cursor.getColumnIndex("description"));
        Integer catId = cursor.getInt(cursor.getColumnIndex("product_cat"));

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setCatId(catId);

        products.add(product);
        cursor.moveToNext();
      }
    }
    return products;
  }

  public List<Product> getProductsById(int categoryId, Context context){
    if(db == null){
      productHelper = new ProductSQLiteHelper(context, "dbDavinci1", null, 1);
      db = productHelper.getWritableDatabase();
    }

    Cursor cursor = db.rawQuery("SELECT * FROM Product where product_cat = " + categoryId, null);
    List<Product> products = new ArrayList<>();

    if (cursor.moveToFirst()) {
      while(cursor.isAfterLast()== false){

        Integer id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String description = cursor.getString(cursor.getColumnIndex("description"));
        Integer catId = cursor.getInt(cursor.getColumnIndex("product_cat"));

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setCatId(catId);

        products.add(product);
        cursor.moveToNext();
      }
    }
    return products;
  }

}
