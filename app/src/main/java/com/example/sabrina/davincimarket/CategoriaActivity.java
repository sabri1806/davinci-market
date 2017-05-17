package com.example.sabrina.davincimarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import models.Category;

/**
 * Created by Sabrina on 13/05/2017.
 */

public class CategoriaActivity extends AppCompatActivity{

    public TextView nom;
    public TextView categorias;
    public ImageButton higiene;
    public ImageButton limpieza;
    public ImageButton bebidas;
    public ImageButton lacteos;
    public ImageButton carniceria;
    public ImageButton verduleria;


    List<Category> categories = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_activity);

        nom = (TextView) findViewById (R.id.nombre);

        Category greenGrossery = new Category();
        greenGrossery.setName("verduleria");
        greenGrossery.setDescription("todo verde");

        Category butchery = new Category();
        butchery.setName("carniceria");
        butchery.setDescription("calne");

        Category backery = new Category();
        backery.setName("panaderia");
        backery.setDescription("pancitos");


        categories.add(greenGrossery);
        categories.add(butchery);
        categories.add(backery);


        final Bundle bun2 = getIntent().getExtras();


        String datoNombre = (String)bun2.get("usuario");

        nom.setText(datoNombre);


        categorias = (TextView) findViewById(R.id.categorias);

       /* higiene = (ImageButton) findViewById(R.id.higiene);
        limpieza = (ImageButton) findViewById(R.id.limpieza);
        bebidas = (ImageButton) findViewById(R.id.bebidas);
        lacteos = (ImageButton) findViewById(R.id.lacteos);
        carniceria = (ImageButton) findViewById(R.id.carniceria);
        verduleria = (ImageButton) findViewById(R.id.verduleria);*/


        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_category);
        int i=1;
        for (Category c: categories) {

        final Button btn = new Button(this);
        final String value = c.getName();

        btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            System.out.println(btn.getId());
            bun2.putString("name",value);
            Intent in = new Intent(CategoriaActivity.this, ProductoActivity.class);
            setContentView(R.layout.producto_activity);
            in.putExtras(bun2);
            startActivity(in);
          }
        });

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //btn.setId(i);
        int top = (i * 150);

        params1.setMargins(100, top, 0, 0);

        btn.setText(c.getName());
        rl.addView(btn,params1);
        i++;
      }



       /* higiene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CategoriaActivity.this, ProductoActivity.class);
                //setContentView(R.layout.producto_activity);

              Bundle bun = new Bundle();

              bun.putString("higiene", categorias.getText().toString());
              in.putExtras(bun);
              startActivity(in);
            }
        });*/


    }
}
