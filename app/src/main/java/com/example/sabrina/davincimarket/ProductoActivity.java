package com.example.sabrina.davincimarket;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import java.util.Date;
import java.util.List;
import models.CartItem;
import models.Product;
import utils.CartListUtils;
import utils.ProductUtils;

/**
 * Created by Sabrina on 14/05/2017.
 */


public class ProductoActivity extends AppCompatActivity{

  private List<Product> products;
  public ProductUtils productUtils = new ProductUtils();
  Bundle bun;
  public Button listo;
  private CartListUtils cartListUtils = new CartListUtils();
  Intent in2;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menutoolbar, menu);

    return super.onCreateOptionsMenu(menu);


  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.lista:

        in2= new Intent(ProductoActivity.this,CarroActivity.class);
        in2.putExtras(bun);
        startActivity(in2);
        break;
      case R.id.settings:
        in2 = new Intent(ProductoActivity.this,SettingsActivity.class);
        in2.putExtras(bun);
        startActivity(in2);

      case R.id.aboutme:
        in2 = new Intent(ProductoActivity.this,AboutMeActivity.class);
        in2.putExtras(bun);
        startActivity(in2);
        break;
    }
    return true;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.producto_activity);

    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);

    cartListUtils.initDb(this);


    bun = getIntent().getExtras();
    Integer categoryId = (Integer)bun.get("id");

    products = productUtils.getProductsById(categoryId, this);

    RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_prod);
    int a = 0;
    int b = 50;
    int i = 2;

      for (final Product prod: products) {

        int drawable = 0;
        switch (prod.getName()) {
          case "manzana":
            drawable = R.drawable.manzanas;
            break;
          case "banana":
            drawable = R.drawable.banana;
            break;
          case "rucula":
            drawable = R.drawable.rucula;
            break;
          case "cebolla":
            drawable = R.drawable.cebollas;
            break;
          case "tomate":
            drawable = R.drawable.tomates;
            break;
          case "limon":
            drawable = R.drawable.limon;
            break;
        }

        if (drawable == 0) {
          continue;
        }

        Button btn = new Button(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //btn.setId(i);

        //btn.setOnClickListener(this);
        if(i % 2 == 0 ){
          //margen top
          a = (i * 125);
          params1.setMargins(b, a, 0, a+500);
          btn.setText(prod.getName());

          //le paso la imagen de producto
          btn.setBackground(getResources().getDrawable(drawable));


          btn.setOnClickListener(new View.OnClickListener() {
            long a = new Date().getTime();

            @Override
            public void onClick(View v) {
              CartItem c1 = new CartItem();
              c1.setId((int)a);
              c1.setName(prod.getName());

              c1.setDescription(prod.getDescription());
              c1.setCartDate(new Date());
              c1.setStatus(true);

              cartListUtils.createItem(c1);
            }
          });

          rl.addView(btn,params1);
          b = 400;
        }else {
          params1.setMargins(b, a, 0, a+500);
          btn.setText(prod.getName());

          btn.setBackground(getResources().getDrawable(drawable));

          btn.setOnClickListener(new View.OnClickListener() {
            long a = new Date().getTime();

            @Override
            public void onClick(View v) {
              CartItem c1 = new CartItem();
              c1.setId((int)a);
              c1.setName(prod.getName());
              c1.setDescription(prod.getDescription());
              c1.setCartDate(new Date());
              c1.setStatus(true);

              cartListUtils.createItem(c1);
            }
          });
          rl.addView(btn,params1);
          b = 50;
        }
        i++;
      }


        listo = (Button)findViewById(R.id.listo);
        //invoca al boton de compra
        listo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent in = new Intent(ProductoActivity.this,CarroActivity.class);
        setContentView(R.layout.carro_activity);
        in.putExtras(bun);
        startActivity(in);

      }
    });

  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    Intent in = new Intent(ProductoActivity.this,CategoriaActivity.class);
    in.putExtras(bun);
    startActivity(in);
  }
}
