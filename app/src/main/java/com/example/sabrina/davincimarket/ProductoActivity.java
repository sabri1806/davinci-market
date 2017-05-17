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

/**
 * Created by Sabrina on 14/05/2017.
 */

//falta agregar que implementa OnClickListener
public class ProductoActivity extends AppCompatActivity{


  List<Category> categories = new ArrayList<>();
  public Category categorySelected;
  Bundle bun;
  public Button listo;
  public Button volver;

  private void getCategoryByName(String nameCategory){

    for (Category c :categories) {
      if (c.getName().equals(nameCategory)){
        //categoria seleccionada
        this.categorySelected = c;

      }
    }

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.producto_activity);

    Category greenGrossery = new Category();
    greenGrossery.setName("verduleria");
    greenGrossery.setDescription("todo verde");

    Category butchery = new Category();
    butchery.setName("carniceria");
    butchery.setDescription("calne");

    Category backery = new Category();
    backery.setName("panaderia");
    backery.setDescription("pancitos");

    Category cleaning = new Category();
    cleaning.setName("limpieza");
    cleaning.setDescription("cocina");

    Product pan1 = new Product();
    pan1.setName("pan frances");
    pan1.setDescription("Bimbo");

    backery.getProducts().add(pan1);

    Product carne1 = new Product();
    carne1.setName("peceto");
    carne1.setDescription("milanesas");

    Product carne2 = new Product();
    carne2.setName("carne picada");
    carne2.setDescription("magra");

    butchery.getProducts().add(carne1);
    butchery.getProducts().add(carne2);

    Product verdu1 = new Product();
    verdu1.setName("tomate");
    verdu1.setDescription("perita");

    greenGrossery.getProducts().add(verdu1);

    Product limp1= new Product();
    limp1.setName("detergente");
    limp1.setDescription("antigrasa");

    cleaning.getProducts().add(limp1);

    categories.add(greenGrossery);
    categories.add(butchery);
    categories.add(backery);
    categories.add(cleaning);



    bun = getIntent().getExtras();
    String datoNombre = (String)bun.get("name");

    this.getCategoryByName(datoNombre);

    RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_prod);
    int a = 0;
    int b = 50;
    int i = 0;
    for (Product prod: categorySelected.getProducts()) {

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

        String [] produ = {"tomate","detergente", "azucar"};

        Intent in = new Intent(ProductoActivity.this,CarroActivity.class);
        setContentView(R.layout.carro_activity);
        bun.putStringArray("produ", produ);

        in.putExtras(bun);
        startActivity(in);

      }
    });

  }




}
