package com.example.sabrina.davincimarket;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import dbHelpers.CategorySQLiteHelper;
import models.Category;
import models.Product;
import utils.CategoryUtils;
import utils.ProductUtils;

/**
 * Created by Sabrina on 13/05/2017.
 */

public class CategoriaActivity extends AppCompatActivity{

    public TextView nom;
    public TextView categorias;



    List<Category> categories = new ArrayList<>();
    List<Product> products = new ArrayList<>();
    private CategoryUtils catUtil = new CategoryUtils();
    private ProductUtils prodUtil = new ProductUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_activity);

        catUtil.initDb(this);
        this.categories = catUtil.getAll();
        prodUtil.initDb(this, categories);
        nom = (TextView) findViewById (R.id.nombre);

        final Bundle bun2 = getIntent().getExtras();


        String datoNombre = (String)bun2.get("usuario");

        nom.setText(datoNombre);


        categorias = (TextView) findViewById(R.id.categorias);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_category);
        int i=1;
        for (Category c: categories) {

            final Button btn = new Button(this);
            final int value = c.getId();


            btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                bun2.putInt("id",value);
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

    @Override
    protected void onDestroy() {
    catUtil.destroyDb();
    super.onDestroy();

    }
}


