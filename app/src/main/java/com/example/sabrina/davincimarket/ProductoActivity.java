package com.example.sabrina.davincimarket;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Product;
import utils.ProductUtils;

/**
 * Created by Sabrina on 14/05/2017.
 */

//falta agregar que implementa OnClickListener
public class ProductoActivity extends AppCompatActivity{


  List<Category> categories = new ArrayList<>();
  public Category categorySelected;
  private List<Product> products;
  public ProductUtils productUtils = new ProductUtils();
  Bundle bun;
  public Button listo;
  public Button volver;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.producto_activity);



    bun = getIntent().getExtras();
    Integer categoryId = (Integer)bun.get("id");

    products = productUtils.getProductsById(categoryId, this);

    RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_prod);
    int a = 0;
    int b = 50;
    int i = 0;

      for (Product prod: products) {

        Button btn = new Button(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //btn.setId(i);

        //btn.setOnClickListener(this);
        if(i % 2 == 0 ){
          a = (i * 100);
          params1.setMargins(b, a, 0, a+500);
          btn.setText(prod.getName());
          rl.addView(btn,params1);
          b = 400;
        }else {
          params1.setMargins(b, a, 0, a+500);
          btn.setText(prod.getName());
          rl.addView(btn,params1);
          b = 50;

        }
        i++;
      }



    volver = (Button)findViewById(R.id.volver);




        listo = (Button)findViewById(R.id.listo);
        //invoca al boton de compra
        listo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

       // String [] produ = {"tomate","detergente", "azucar"};

        Intent in = new Intent(ProductoActivity.this,CarroActivity.class);
        setContentView(R.layout.carro_activity);
      //

        //  bun.putStringArray("produ", produ);
        in.putExtras(bun);
        startActivity(in);

      }
    });

  }




}
